package com.example.movplayv3.data.paging.tvshow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowEntity
import com.example.movplayv3.data.model.tvshow.TvShowEntityType
import com.example.movplayv3.data.model.tvshow.TvShowsRemoteKeys
import com.example.movplayv3.data.model.tvshow.TvShowsResponse
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class TvShowsRemotePagingMediator(
    private val deviceLanguage: DeviceLanguage,
    private val type: TvShowEntityType,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, TvShowEntity>() {
    private val tvShowsDao = appDatabase.tvShowsDao()
    private val tvShowRemoteKeysDao = appDatabase.tvShowsRemoteKeysDao()

    private val apiHelperMethod: suspend (Int, String, String) -> TvShowsResponse = when (type) {
        TvShowEntityType.AiringToday -> apiTvShowHelper::getAiringTodayTvShows
        TvShowEntityType.TopRated -> apiTvShowHelper::getTopRatedTvShows
        TvShowEntityType.Trending -> apiTvShowHelper::getTrendingTvShows
        TvShowEntityType.Popular -> apiTvShowHelper::getPopularTvShows
        TvShowEntityType.Discover -> apiTvShowHelper::discoverTvShows
    }

    override suspend fun initialize(): InitializeAction {
        val remoteKey = appDatabase.withTransaction {
            tvShowRemoteKeysDao.getRemoteKey(
                type = type,
                language = deviceLanguage.languageCode
            )
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
        state: PagingState<Int, TvShowEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.APPEND -> {
                    val remoteKey = appDatabase.withTransaction {
                        tvShowRemoteKeysDao.getRemoteKey(
                            type = type,
                            language = deviceLanguage.languageCode
                        )
                    } ?: return MediatorResult.Success(true)

                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(true)
                    }
                    remoteKey.nextPage
                }
            }

            val result = apiHelperMethod(page, deviceLanguage.languageCode, deviceLanguage.region)

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    tvShowsDao.deleteTvShowsOfType(
                        type = type,
                        language = deviceLanguage.languageCode
                    )
                    tvShowRemoteKeysDao.deleteRemoteKeysOfType(
                        type = type,
                        language = deviceLanguage.languageCode
                    )
                }

                val nextPage = if (result.tvShows.isNotEmpty()) {
                    page + 1
                } else null

                val tvShowEntities = result.tvShows.map { tvSeries ->
                    TvShowEntity(
                        id = tvSeries.id,
                        type = type,
                        title = tvSeries.title,
                        originalName = tvSeries.originalName,
                        posterPath = tvSeries.posterPath,
                        language = deviceLanguage.languageCode
                    )
                }

                tvShowRemoteKeysDao.insertKey(
                    TvShowsRemoteKeys(
                        language = deviceLanguage.languageCode,
                        type = type,
                        nextPage = nextPage,
                        lastUpdated = System.currentTimeMillis()
                    )
                )
                tvShowsDao.addTvShow(tvShowEntities)
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