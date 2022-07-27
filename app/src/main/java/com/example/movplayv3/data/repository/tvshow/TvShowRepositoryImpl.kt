package com.example.movplayv3.data.repository.tvshow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.*
import com.example.movplayv3.data.paging.tvshow.DiscoverTvShowsPagingDataSource
import com.example.movplayv3.data.paging.tvshow.TvShowDetailsPagingRemoteMediator
import com.example.movplayv3.data.paging.tvshow.TvShowDetailsResponsePagingDataSource
import com.example.movplayv3.data.paging.tvshow.TvShowsRemotePagingMediator
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class TvShowRepositoryImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val appDatabase: AppDatabase
) : TvShowRepository {
    override fun discoverTvShows(
        deviceLanguage: DeviceLanguage,
        sortType: SortType,
        sortOrder: SortOrder,
        genresParam: GenresParam,
        watchProvidersParam: WatchProvidersParam,
        voteRange: ClosedFloatingPointRange<Float>,
        onlyWithPosters: Boolean,
        onlyWithScore: Boolean,
        onlyWithOverview: Boolean,
        airDateRange: DateRange
    ): Flow<PagingData<TvShow>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        DiscoverTvShowsPagingDataSource(
            apiTvShowsHelper = apiTvShowHelper,
            deviceLanguage = deviceLanguage,
            sortType = sortType,
            sortOrder = sortOrder,
            genresParam = genresParam,
            watchProvidersParam = watchProvidersParam,
            voteRange = voteRange,
            onlyWithPosters = onlyWithPosters,
            onlyWithScore = onlyWithScore,
            onlyWithOverview = onlyWithOverview,
            airDateRange = airDateRange
        )
    }.flow.flowOn(defaultDispatcher)

    override fun topRatedTvShows(deviceLanguage: DeviceLanguage): Flow<PagingData<TvShowEntity>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = TvShowsRemotePagingMediator(
                deviceLanguage = deviceLanguage,
                apiTvShowHelper = apiTvShowHelper,
                appDatabase = appDatabase,
                type = TvShowEntityType.TopRated
            ),
            pagingSourceFactory = {
                appDatabase.tvShowsDao().getAllTvShows(
                    type = TvShowEntityType.TopRated,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun onTheAirTvShows(deviceLanguage: DeviceLanguage): Flow<PagingData<TvShowDetailEntity>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = TvShowDetailsPagingRemoteMediator(
                deviceLanguage = deviceLanguage,
                apiTvShowHelper = apiTvShowHelper,
                appDatabase = appDatabase,
            ),
            pagingSourceFactory = {
                appDatabase.tvShowsDetailsDao().getAllTvShows(
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun trendingTvShows(deviceLanguage: DeviceLanguage): Flow<PagingData<TvShowEntity>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = TvShowsRemotePagingMediator(
                deviceLanguage = deviceLanguage,
                apiTvShowHelper = apiTvShowHelper,
                appDatabase = appDatabase,
                type = TvShowEntityType.Trending
            ),
            pagingSourceFactory = {
                appDatabase.tvShowsDao().getAllTvShows(
                    type = TvShowEntityType.Trending,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun popularTvShows(deviceLanguage: DeviceLanguage): Flow<PagingData<TvShowEntity>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = TvShowsRemotePagingMediator(
                deviceLanguage = deviceLanguage,
                apiTvShowHelper = apiTvShowHelper,
                appDatabase = appDatabase,
                type = TvShowEntityType.Popular
            ),
            pagingSourceFactory = {
                appDatabase.tvShowsDao().getAllTvShows(
                    type = TvShowEntityType.Popular,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun airingTodayTvShows(deviceLanguage: DeviceLanguage): Flow<PagingData<TvShowEntity>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = TvShowsRemotePagingMediator(
                deviceLanguage = deviceLanguage,
                apiTvShowHelper = apiTvShowHelper,
                appDatabase = appDatabase,
                type = TvShowEntityType.AiringToday
            ),
            pagingSourceFactory = {
                appDatabase.tvShowsDao().getAllTvShows(
                    type = TvShowEntityType.AiringToday,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun similarTvShows(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<TvShow>> = Pager(
        PagingConfig(pageSize = 20)
    ){
        TvShowDetailsResponsePagingDataSource(
            tvShowId = tvShowId,
            deviceLanguage = deviceLanguage,
            apiHelperMethod = apiTvShowHelper::getSimilarTvShows
        )
    }.flow.flowOn(defaultDispatcher)

    override fun tvShowsRecommendations(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<TvShow>> {
        TODO("Not yet implemented")
    }

    override fun getTvShowDetails(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<TvShowDetails>> {
        TODO("Not yet implemented")
    }

    override fun tvShowImages(tvShowId: Int): Call<ImagesResponse> {
        TODO("Not yet implemented")
    }

    override fun tvShowReviews(tvShowId: Int): Flow<PagingData<Review>> {
        TODO("Not yet implemented")
    }

    override fun tvShowReview(tvShowId: Int): Flow<PagingData<ReviewsResponse>> {
        TODO("Not yet implemented")
    }

    override fun watchProviders(tvShowId: Int): Flow<PagingData<WatchProvidersResponse>> {
        TODO("Not yet implemented")
    }

    override fun getExternalIds(tvShowId: Int): Flow<PagingData<ExternalIds>> {
        TODO("Not yet implemented")
    }

    override fun tvShowVideos(tvShowId: Int, isoCode: String): Call<VideosResponse> {
        TODO("Not yet implemented")
    }
}