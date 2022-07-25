package com.example.movplayv3.data.paging.tvshow

import androidx.paging.*
import androidx.room.withTransaction
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowDetailEntity
import com.example.movplayv3.data.model.tvshow.TvShowDetailsRemoteKey
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class TvShowDetailsPagingRemoteMediator(
    private val deviceLanguage: DeviceLanguage,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, TvShowDetailEntity>() {
    private val tvShowDetailsDao = appDatabase.tvShowsDetailsDao()
    private val tvShowDetailsRemoteKeysDao = appDatabase.tvShowsDetailsRemoteKeys()

    override suspend fun initialize(): InitializeAction {
        val remoteKey = appDatabase.withTransaction {
            tvShowDetailsRemoteKeysDao.getRemoteKey(deviceLanguage.languageCode)
        } ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)

        return if ((System.currentTimeMillis() - remoteKey.lastUpdated) >= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvShowDetailEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.APPEND -> {
                    val remoteKey = appDatabase.withTransaction {
                        tvShowDetailsRemoteKeysDao.getRemoteKey(deviceLanguage.languageCode)
                    } ?: return MediatorResult.Success(true)

                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(true)
                    }

                    remoteKey.nextPage
                }
            }

            val result = apiTvShowHelper.getOnTheAirTvShows(
                page = page,
                isoCode = deviceLanguage.languageCode,
                region = deviceLanguage.region
            )

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    tvShowDetailsDao.deleteAllTvShows(deviceLanguage.languageCode)
                    tvShowDetailsRemoteKeysDao.deleteKeys(deviceLanguage.languageCode)
                }

                val nextPage = if (result.tvShows.isNotEmpty()) {
                    page + 1
                } else null

                val tvShowEntities = result.tvShows.map { tvShow ->
                    TvShowDetailEntity(
                        id = tvShow.id,
                        title = tvShow.title,
                        originalTitle = tvShow.originalName,
                        posterPath = tvShow.posterPath,
                        backdropPath = tvShow.backdropPath,
                        overview = tvShow.overview,
                        adult = tvShow.adult,
                        voteAverage = tvShow.voteAverage,
                        voteCount = tvShow.voteCount,
                        language = deviceLanguage.languageCode
                    )
                }
                tvShowDetailsRemoteKeysDao.insertKey(
                    TvShowDetailsRemoteKey(
                        language = deviceLanguage.languageCode,
                        nextPage = nextPage,
                        lastUpdated = System.currentTimeMillis()
                    )
                )
                tvShowDetailsDao.addTvShow(tvShowEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = result.tvShows.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: JsonDataException) {
            FirebaseCrashlytics.getInstance().recordException(e)
            MediatorResult.Error(e)
        }
    }
}