//package com.example.movplayv3.data.remote.api
//
//import com.example.movplayv3.data.model.*
//import com.example.movplayv3.data.model.movie.MovieDetails
//import com.example.movplayv3.data.model.movie.MoviesResponse
//import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
//import com.example.movplayv3.data.model.tvshow.TvShowDetails
//import com.example.movplayv3.data.model.tvshow.TvShowsResponse
//import retrofit2.Call
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class TmdbApiHelperImpl @Inject constructor(
//    private val tmdbApi: TmdbApi
//) : TmdbApiHelper {
//    override fun getConfig(): Call<Config> {
//        return tmdbApi.getConfig()
//    }
//
//    override suspend fun discoverMovies(
//        page: Int,
//        isoCode: String,
//        region: String,
//        sortTypeParam: SortTypeParam,
//        genresParam: GenresParam,
//        watchProvidersParam: WatchProvidersParam,
//        voteRange: ClosedFloatingPointRange<Float>,
//        fromReleaseDate: DateParam?,
//        toReleaseDate: DateParam?
//    ): MoviesResponse {
//        return tmdbApi.discoverMovies(
//            page,
//            isoCode,
//            region,
//            sortTypeParam,
//            genresParam,
//            watchProvidersParam,
//            voteAverageMin = voteRange.start,
//            voteAverageMax = voteRange.endInclusive,
//            fromReleaseDate = fromReleaseDate,
//            toReleaseDate = toReleaseDate
//        )
//    }
//
//    override suspend fun getPopularMovies(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): MoviesResponse {
//        return tmdbApi.getPopularMovies(page, isoCode, region)
//    }
//
//    override suspend fun getUpcomingMovies(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): MoviesResponse {
//        return tmdbApi.getUpcomingMovies(page, isoCode, region)
//    }
//
//    override suspend fun getTopRatedMovies(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): MoviesResponse {
//        return tmdbApi.getTopRatedMovies(page, isoCode, region)
//    }
//
//    override suspend fun getNowPlayingMovies(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): MoviesResponse {
//        return tmdbApi.getNowPlayingMovies(page, isoCode, region)
//    }
//
//    override fun getMovieDetails(movieId: Int, isoCode: String): Call<MovieDetails> {
//        return tmdbApi.getMovieDetails(movieId, isoCode)
//    }
//
//    override fun getMovieCredits(movieId: Int, isoCode: String): Call<Credits> {
//        return tmdbApi.getMovieCredits(movieId, isoCode)
//    }
//
//    override suspend fun getSimilarMovies(
//        movieId: Int,
//        page: Int,
//        isoCode: String,
//        region: String
//    ): MoviesResponse {
//        return tmdbApi.getSimilarMovies(movieId, page, isoCode, region)
//    }
//
//    override suspend fun getMoviesRecommendations(
//        movieId: Int,
//        page: Int,
//        isoCode: String,
//        region: String
//    ): MoviesResponse {
//        return tmdbApi.getMoviesRecommendations(movieId, page, isoCode, region)
//    }
//
//    override suspend fun getTrendingMovies(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): MoviesResponse {
//        return tmdbApi.getTrendingMovies(page, isoCode, region)
//    }
//
//    override fun getMovieImages(movieId: Int): Call<ImagesResponse> {
//        return tmdbApi.getMovieImages(movieId)
//    }
//
//    override suspend fun getMovieReviews(movieId: Int, page: Int): ReviewsResponse {
//        return tmdbApi.getMovieReviews(movieId, page)
//    }
//
//    override fun getMovieReview(movieId: Int): Call<ReviewsResponse> {
//        return tmdbApi.getMovieReview(movieId)
//    }
//
//    override fun getMoviesGenres(isoCode: String): Call<GenresResponse> {
//        return tmdbApi.getMovieGenres(isoCode)
//    }
//
//    override fun getMovieWatchProviders(movieId: Int): Call<WatchProvidersResponse> {
//        return tmdbApi.getMovieWatchProviders(movieId)
//    }
//
//    override fun getMovieExternalIds(movieId: Int): Call<ExternalIds> {
//        return tmdbApi.getMovieExternalIds(movieId)
//    }
//
//    override fun getAllMoviesWatchProviders(
//        isoCode: String,
//        region: String
//    ): Call<AllWatchProvidersResponse> {
//        return tmdbApi.getAllMoviesWatchProviders(isoCode, region)
//    }
//
//    override fun getMovieVideos(movieId: Int, isoCode: String): Call<VideosResponse> {
//        return tmdbApi.getMovieVideos(movieId, isoCode)
//    }
//
//    override suspend fun getOtherMoviesOfDirector(
//        page: Int,
//        isoCode: String,
//        region: String,
//        directorId: Int
//    ): MoviesResponse {
//        return tmdbApi.getOtherMoviesOfDirector(page, isoCode, region, directorId)
//    }
//
//    override suspend fun discoverTvShows(
//        page: Int,
//        isoCode: String,
//        region: String,
//        sortTypeParam: SortTypeParam,
//        genresParam: GenresParam,
//        watchProvidersParam: WatchProvidersParam,
//        voteRange: ClosedFloatingPointRange<Float>,
//        fromAirDate: DateParam?,
//        toAirDate: DateParam?
//    ): TvShowsResponse {
//        return tmdbApi.discoverTvShows(
//            page,
//            isoCode,
//            region,
//            sortTypeParam,
//            genresParam,
//            watchProvidersParam,
//            voteAverageMin = voteRange.start,
//            voteAverageMax = voteRange.endInclusive,
//            fromAirDate = fromAirDate,
//            toAirDate = toAirDate
//        )
//    }
//
//    override suspend fun getTopRatedTvShows(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): TvShowsResponse {
//        return tmdbApi.getTopRatedTvShows(page, isoCode, region)
//    }
//
//    override suspend fun getOnTheAirTvShows(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): TvShowsResponse {
//        return tmdbApi.getOnTheAirTvShows(page, isoCode, region)
//    }
//
//    override suspend fun getPopularTvShows(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): TvShowsResponse {
//        return tmdbApi.getPopularTvShows(page, isoCode, region)
//    }
//
//    override suspend fun getAiringTodayTvShows(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): TvShowsResponse {
//        return tmdbApi.getAiringTodayTvShows(page, isoCode, region)
//    }
//
//    override fun getTvShowDetails(tvShowId: Int, isoCode: String): Call<TvShowDetails> {
//        return tmdbApi.getTvShowDetails(tvShowId, isoCode)
//    }
//
//    override suspend fun getSimilarTvShows(
//        tvShowId: Int,
//        page: Int,
//        isoCode: String,
//        region: String
//    ): TvShowsResponse {
//        return tmdbApi.getSimilarTvShows(tvShowId, page, isoCode, region)
//    }
//
//    override suspend fun getTvShowsRecommendations(
//        tvShowId: Int,
//        page: Int,
//        isoCode: String,
//        region: String
//    ): TvShowsResponse {
//        return tmdbApi.getTvShowsRecommendations(tvShowId, page, isoCode, region)
//    }
//
//    override fun getTvSeasons(
//        tvShowId: Int,
//        seasonNumber: Int,
//        isoCode: String
//    ): Call<TvSeasonsResponse> {
//        return tmdbApi.getTvSeasons(tvShowId, seasonNumber, isoCode)
//    }
//
//    override suspend fun getTrendingTvShows(
//        page: Int,
//        isoCode: String,
//        region: String
//    ): TvShowsResponse {
//        return tmdbApi.getTrendingTvShows(page, isoCode, region)
//    }
//
//    override fun getSeasonDetails(
//        tvShowId: Int,
//        seasonNumber: Int,
//        isoCode: String
//    ): Call<SeasonDetails> {
//        return tmdbApi.getSeasonDetails(tvShowId, seasonNumber, isoCode)
//    }
//
//    override fun getTvShowImages(tvShowId: Int): Call<ImagesResponse> {
//        return tmdbApi.getTvShowImages(tvShowId)
//    }
//
//    override fun getEpisodeImages(
//        tvShowId: Int,
//        seasonNumber: Int,
//        episodeNumber: Int
//    ): Call<ImagesResponse> {
//        return tmdbApi.getEpisodeImages(tvShowId, seasonNumber, episodeNumber)
//    }
//
//    override suspend fun getTvShowReviews(tvShowId: Int, page: Int): ReviewsResponse {
//        return tmdbApi.getTvShowReviews(tvShowId, page)
//    }
//
//    override fun getTvShowReview(tvShowId: Int): Call<ReviewsResponse> {
//        return tmdbApi.getTvShowReview(tvShowId)
//    }
//
//    override fun getTvShowsGenres(isoCode: String): Call<GenresResponse> {
//        return tmdbApi.getTvShowsGenres(isoCode)
//    }
//
//    override fun getTvShowWatchProviders(tvShowId: Int): Call<WatchProvidersResponse> {
//        return tmdbApi.getTvShowWatchProviders(tvShowId)
//    }
//
//    override fun getTvShowExternalIds(tvShowId: Int): Call<ExternalIds> {
//        return tmdbApi.getTvShowExternalIds(tvShowId)
//    }
//
//    override fun getAllTvShowsWatchProviders(
//        isoCode: String,
//        region: String
//    ): Call<AllWatchProvidersResponse> {
//        return tmdbApi.getAllTvShowsWatchProviders(isoCode, region)
//    }
//
//    override fun getTvShowVideos(tvShowId: Int, isoCode: String): Call<VideosResponse> {
//        return tmdbApi.getTvShowVideos(tvShowId, isoCode)
//    }
//
//    override fun getSeasonVideos(
//        tvShowId: Int,
//        seasonNumber: Int,
//        isoCode: String
//    ): Call<VideosResponse> {
//        return tmdbApi.getSeasonVideos(tvShowId, seasonNumber, isoCode)
//    }
//
//    override fun getSeasonCredits(
//        tvShowId: Int,
//        seasonNumber: Int,
//        isoCode: String
//    ): Call<AggregatedCredits> {
//        return tmdbApi.getSeasonCredits(tvShowId, seasonNumber, isoCode)
//    }
//
//
//    override suspend fun multiSearch(
//        page: Int,
//        isoCode: String,
//        region: String,
//        query: String,
//        includeAdult: Boolean,
//        year: Int?,
//        releaseYear: Int?
//    ): SearchResponse {
//        return tmdbApi.multiSearch(
//            page = page,
//            isoCode = isoCode,
//            region = region,
//            query = query,
//            includeAdult = includeAdult,
//            year = year,
//            releaseYear = releaseYear
//        )
//    }
//
//    override fun getCollection(collectionId: Int, isoCode: String): Call<CollectionResponse> {
//        return tmdbApi.getCollection(
//            collectionId, isoCode
//        )
//    }
//
//    override fun getPersonDetails(personId: Int, isoCode: String): Call<PersonDetails> {
//        return tmdbApi.getPersonDetails(personId, isoCode)
//    }
//
//    override fun getCombinedCredits(personId: Int, isoCode: String): Call<CombinedCredits> {
//        return tmdbApi.getCombinedCredits(personId, isoCode)
//    }
//
//    override fun getPersonExternalIds(personId: Int, isoCode: String): Call<ExternalIds> {
//        return tmdbApi.getPersonExternalIds(personId, isoCode)
//    }
//}