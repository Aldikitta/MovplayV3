//package com.example.movplayv3.data.remote.api
//
//import com.example.movplayv3.data.model.*
//import com.example.movplayv3.data.model.movie.MovieDetails
//import com.example.movplayv3.data.model.movie.MoviesResponse
//import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
//import com.example.movplayv3.data.model.tvshow.TvShowDetails
//import com.example.movplayv3.data.model.tvshow.TvShowsResponse
//import retrofit2.Call
//
//interface TmdbApiHelper {
//    fun getConfig(): Call<Config>
//
//
//    suspend fun discoverMovies(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region,
//        sortTypeParam: SortTypeParam = SortTypeParam.PopularityDesc,
//        genresParam: GenresParam = GenresParam(genres = emptyList()),
//        watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
//        voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
//        fromReleaseDate: DateParam? = null,
//        toReleaseDate: DateParam? = null
//    ): MoviesResponse
//
//    suspend fun getPopularMovies(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region,
//    ): MoviesResponse
//
//    suspend fun getUpcomingMovies(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region,
//    ): MoviesResponse
//
//    suspend fun getTopRatedMovies(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region,
//    ): MoviesResponse
//
//    suspend fun getNowPlayingMovies(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): MoviesResponse
//
//    fun getMovieDetails(
//        movieId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<MovieDetails>
//
//    fun getMovieCredits(
//        movieId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<Credits>
//
//    suspend fun getSimilarMovies(
//        movieId: Int,
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): MoviesResponse
//
//    suspend fun getMoviesRecommendations(
//        movieId: Int,
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): MoviesResponse
//
//    suspend fun getTrendingMovies(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): MoviesResponse
//
//    fun getMovieImages(movieId: Int): Call<ImagesResponse>
//
//    suspend fun getMovieReviews(movieId: Int, page: Int): ReviewsResponse
//
//    fun getMovieReview(movieId: Int): Call<ReviewsResponse>
//
//    fun getMoviesGenres(isoCode: String = DeviceLanguage.default.languageCode): Call<GenresResponse>
//
//    fun getMovieWatchProviders(
//        movieId: Int
//    ): Call<WatchProvidersResponse>
//
//    fun getMovieExternalIds(
//        movieId: Int
//    ): Call<ExternalIds>
//
//    fun getAllMoviesWatchProviders(
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): Call<AllWatchProvidersResponse>
//
//    fun getMovieVideos(
//        movieId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//    ): Call<VideosResponse>
//
//    suspend fun getOtherMoviesOfDirector(
//        page: Int,
//        isoCode: String,
//        region: String,
//        directorId: Int
//    ): MoviesResponse
//
//
//    suspend fun discoverTvShows(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region,
//        sortTypeParam: SortTypeParam = SortTypeParam.PopularityDesc,
//        genresParam: GenresParam = GenresParam(genres = emptyList()),
//        watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
//        voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
//        fromAirDate: DateParam? = null,
//        toAirDate: DateParam? = null
//    ): TvShowsResponse
//
//    suspend fun getTopRatedTvShows(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): TvShowsResponse
//
//    suspend fun getOnTheAirTvShows(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): TvShowsResponse
//
//    suspend fun getPopularTvShows(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): TvShowsResponse
//
//    suspend fun getAiringTodayTvShows(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): TvShowsResponse
//
//    fun getTvShowDetails(
//        tvShowId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<TvShowDetails>
//
//    suspend fun getSimilarTvShows(
//        tvShowId: Int,
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): TvShowsResponse
//
//    suspend fun getTvShowsRecommendations(
//        tvShowId: Int,
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): TvShowsResponse
//
//    fun getTvSeasons(
//        tvShowId: Int,
//        seasonNumber: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<TvSeasonsResponse>
//
//    suspend fun getTrendingTvShows(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): TvShowsResponse
//
//    fun getSeasonDetails(
//        tvShowId: Int,
//        seasonNumber: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<SeasonDetails>
//
//    fun getTvShowImages(tvShowId: Int): Call<ImagesResponse>
//
//    fun getEpisodeImages(
//        tvShowId: Int,
//        seasonNumber: Int,
//        episodeNumber: Int
//    ): Call<ImagesResponse>
//
//    suspend fun getTvShowReviews(tvShowId: Int, page: Int): ReviewsResponse
//
//    fun getTvShowReview(tvShowId: Int): Call<ReviewsResponse>
//
//    fun getTvShowsGenres(isoCode: String = DeviceLanguage.default.languageCode): Call<GenresResponse>
//
//    fun getTvShowWatchProviders(
//        tvShowId: Int
//    ): Call<WatchProvidersResponse>
//
//    fun getTvShowExternalIds(
//        tvShowId: Int
//    ): Call<ExternalIds>
//
//    fun getAllTvShowsWatchProviders(
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region
//    ): Call<AllWatchProvidersResponse>
//
//    fun getTvShowVideos(
//        tvShowId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//    ): Call<VideosResponse>
//
//    fun getSeasonVideos(
//        tvShowId: Int,
//        seasonNumber: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//    ): Call<VideosResponse>
//
//    fun getSeasonCredits(
//        tvShowId: Int,
//        seasonNumber: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<AggregatedCredits>
//
//
//    suspend fun multiSearch(
//        page: Int,
//        isoCode: String = DeviceLanguage.default.languageCode,
//        region: String = DeviceLanguage.default.region,
//        query: String,
//        includeAdult: Boolean = false,
//        year: Int? = null,
//        releaseYear: Int? = null
//    ): SearchResponse
//
//    fun getCollection(
//        collectionId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<CollectionResponse>
//
//    fun getPersonDetails(
//        personId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<PersonDetails>
//
//    fun getCombinedCredits(
//        personId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<CombinedCredits>
//
//    fun getPersonExternalIds(
//        personId: Int,
//        isoCode: String = DeviceLanguage.default.languageCode
//    ): Call<ExternalIds>
//
//}