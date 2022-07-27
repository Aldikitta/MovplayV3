package com.example.movplayv3.data.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.*
import com.example.movplayv3.data.paging.ReviewsPagingDataSource
import com.example.movplayv3.data.paging.movie.*
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper
import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val apiMovieHelper: TmdbMoviesApiHelper,
    private val appDatabase: AppDatabase,
    private val apiOtherHelper: TmdbOthersApiHelper
) : MovieRepository {
    override fun discoverMovies(
        deviceLanguage: DeviceLanguage,
        sortType: SortType,
        sortOrder: SortOrder,
        genresParam: GenresParam,
        watchProvidersParam: WatchProvidersParam,
        voteRange: ClosedFloatingPointRange<Float>,
        onlyWithPosters: Boolean,
        onlyWithScore: Boolean,
        onlyWithOverview: Boolean,
        releaseDateRange: DateRange
    ): Flow<PagingData<Movie>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        DiscoverMoviesPagingDataSource(
            apiMovieHelper = apiMovieHelper,
            deviceLanguage = deviceLanguage,
            sortType = sortType,
            sortOrder = sortOrder,
            genresParam = genresParam,
            watchProvidersParam = watchProvidersParam,
            voteRange = voteRange,
            onlyWithPosters = onlyWithPosters,
            onlyWithScore = onlyWithScore,
            onlyWithOverview = onlyWithOverview,
            releaseDateRange = releaseDateRange
        )
    }.flow.flowOn(defaultDispatcher)

    override fun popularMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemotePagingMediator(
                deviceLanguage = deviceLanguage,
                apiMovieHelper = apiMovieHelper,
                appDatabase = appDatabase,
                type = MovieEntityType.Popular
            ),
            pagingSourceFactory = {
                appDatabase.moviesDao().getAllMovies(
                    type = MovieEntityType.Popular,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun upcomingMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemotePagingMediator(
                deviceLanguage = deviceLanguage,
                apiMovieHelper = apiMovieHelper,
                appDatabase = appDatabase,
                type = MovieEntityType.Upcoming
            ),
            pagingSourceFactory = {
                appDatabase.moviesDao().getAllMovies(
                    type = MovieEntityType.Upcoming,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun trendingMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemotePagingMediator(
                apiMovieHelper = apiMovieHelper,
                deviceLanguage = deviceLanguage,
                appDatabase = appDatabase,
                type = MovieEntityType.Trending
            ),
            pagingSourceFactory = {
                appDatabase.moviesDao().getAllMovies(
                    type = MovieEntityType.Trending,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun topRatedMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemotePagingMediator(
                apiMovieHelper = apiMovieHelper,
                deviceLanguage = deviceLanguage,
                appDatabase = appDatabase,
                type = MovieEntityType.TopRated
            ),
            pagingSourceFactory = {
                appDatabase.moviesDao().getAllMovies(
                    type = MovieEntityType.TopRated,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)


    override fun nowPlayingMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieDetailEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MovieDetailsPagingRemoteMediator(
                apiMovieHelper = apiMovieHelper,
                deviceLanguage = deviceLanguage,
                appDatabase = appDatabase,
            ),
            pagingSourceFactory = {
                appDatabase.moviesDetailsDao().getAllMovies(
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun similarMovies(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        MovieDetailsResponsePagingDataSource(
            movieId = movieId,
            language = deviceLanguage.languageCode,
            apiHMovieHelperMethod = apiMovieHelper::getSimilarMovies
        )
    }.flow.flowOn(defaultDispatcher)

    override fun moviesRecommendation(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        MovieDetailsResponsePagingDataSource(
            movieId = movieId,
            language = deviceLanguage.languageCode,
            apiHMovieHelperMethod = apiMovieHelper::getMoviesRecommendations
        )
    }.flow.flowOn(defaultDispatcher)

    override fun movieDetails(movieId: Int, isoCode: String): Call<MovieDetails> {
        return apiMovieHelper.getMovieDetails(movieId, isoCode)
    }

    override fun movieCredits(movieId: Int, isoCode: String): Call<Credits> {
        return apiMovieHelper.getMovieCredits(movieId, isoCode)
    }

    override fun movieImages(movieId: Int): Call<ImagesResponse> {
        return apiMovieHelper.getMovieImages(movieId)
    }

    override fun movieReviews(movieId: Int): Flow<PagingData<Review>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        ReviewsPagingDataSource(
            mediaId = movieId,
            apiHelperMethod = apiMovieHelper::getMovieReviews
        )
    }.flow.flowOn(defaultDispatcher)

    override fun movieReview(movieId: Int): Call<ReviewsResponse> {
        return apiMovieHelper.getMovieReview(movieId)
    }

    override fun collection(collectionId: Int, isoCode: String): Call<CollectionResponse> {
        return apiOtherHelper.getCollection(collectionId, isoCode)
    }

    override fun watchProviders(movieId: Int): Call<WatchProvidersResponse> {
        return apiMovieHelper.getMovieWatchProviders(movieId)
    }

    override fun getExternalIds(movieId: Int): Call<ExternalIds> {
        return apiMovieHelper.getMovieExternalIds(movieId)
    }

    override fun getMovieVideos(movieId: Int, isoCode: String): Call<VideosResponse> {
        return apiMovieHelper.getMovieVideos(movieId)
    }

    override fun moviesOfDirector(
        directorId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        DirectorOtherMoviePagingDataSource(
            apiMovieHelper = apiMovieHelper,
            language = deviceLanguage.languageCode,
            region = deviceLanguage.region,
            directorId = directorId
        )
    }.flow.flowOn(defaultDispatcher)
}