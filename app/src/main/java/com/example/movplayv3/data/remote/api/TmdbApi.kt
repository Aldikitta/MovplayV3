//package com.example.movplayv3.data.remote.api
//
//import androidx.annotation.FloatRange
//import com.example.movplayv3.data.model.*
//import com.example.movplayv3.data.model.movie.MovieDetails
//import com.example.movplayv3.data.model.movie.MoviesResponse
//import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
//import com.example.movplayv3.data.model.tvshow.TvShowDetails
//import com.example.movplayv3.data.model.tvshow.TvShowsResponse
//import retrofit2.Call
//import retrofit2.http.GET
//import retrofit2.http.Path
//import retrofit2.http.Query
//
//interface TmdbApi {
//    @GET("configuration")
//    fun getConfig(): Call<Config>
//
//    @GET("discover/movie")
//    suspend fun discoverMovies(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String,
//        @Query("sort_by") type: SortTypeParam,
//        @Query("with_genres") genres: GenresParam,
//        @Query("with_watch_providers") watchProviders: WatchProvidersParam,
//        @Query("watch_region") watchRegion: String = region,
//        @FloatRange(from = 0.0)
//        @Query("vote_average.gte")
//        voteAverageMin: Float,
//        @FloatRange(from = 0.0)
//        @Query("vote_average.lte")
//        voteAverageMax: Float,
//        @Query("release_date.gte")
//        fromReleaseDate: DateParam?,
//        @Query("release_date.lte")
//        toReleaseDate: DateParam?
//    ): MoviesResponse
//
//    @GET("movie/popular")
//    suspend fun getPopularMovies(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): MoviesResponse
//
//    @GET("movie/upcoming")
//    suspend fun getUpcomingMovies(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): MoviesResponse
//
//    @GET("movie/top_rated")
//    suspend fun getTopRatedMovies(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): MoviesResponse
//
//    @GET("movie/now_playing")
//    suspend fun getNowPlayingMovies(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): MoviesResponse
//
//    @GET("movie/{movie_id}")
//    fun getMovieDetails(
//        @Path("movie_id") movieId: Int,
//        @Query("language") isoCode: String
//    ): Call<MovieDetails>
//
//    @GET("movie/{movie_id}/credits")
//    fun getMovieCredits(
//        @Path("movie_id") movieId: Int,
//        @Query("language") isoCode: String
//    ): Call<Credits>
//
//    @GET("movie/{movie_id}/similar")
//    suspend fun getSimilarMovies(
//        @Path("movie_id") movieId: Int,
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): MoviesResponse
//
//    @GET("movie/{movie_id}/recommendations")
//    suspend fun getMoviesRecommendations(
//        @Path("movie_id") movieId: Int,
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): MoviesResponse
//
//    @GET("trending/movie/week")
//    suspend fun getTrendingMovies(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): MoviesResponse
//
//    @GET("movie/{movie_id}/images")
//    fun getMovieImages(
//        @Path("movie_id") movieId: Int
//    ): Call<ImagesResponse>
//
//    @GET("movie/{movie_id}/reviews")
//    suspend fun getMovieReviews(
//        @Path("movie_id") movieId: Int,
//        @Query("page") page: Int
//    ): ReviewsResponse
//
//    @GET("movie/{movie_id}/reviews")
//    fun getMovieReview(
//        @Path("movie_id") movieId: Int
//    ): Call<ReviewsResponse>
//
//    @GET("genre/movie/list")
//    fun getMovieGenres(
//        @Query("language") isoCode: String
//    ): Call<GenresResponse>
//
//    @GET("movie/{movie_id}/watch/providers")
//    fun getMovieWatchProviders(
//        @Path("movie_id") movieId: Int
//    ): Call<WatchProvidersResponse>
//
//    @GET("movie/{movie_id}/external_ids")
//    fun getMovieExternalIds(
//        @Path("movie_id") movieId: Int,
//    ): Call<ExternalIds>
//
//    @GET("watch/providers/movie")
//    fun getAllMoviesWatchProviders(
//        @Query("language") isoCode: String,
//        @Query("watch_region") region: String
//    ): Call<AllWatchProvidersResponse>
//
//    @GET("movie/{movie_id}/videos")
//    fun getMovieVideos(
//        @Path("movie_id") movieId: Int,
//        @Query("language") isoCode: String
//    ): Call<VideosResponse>
//
//    @GET("discover/movie")
//    suspend fun getOtherMoviesOfDirector(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String,
//        @Query("with_crew") directorId: Int
//    ): MoviesResponse
//
//
//
//    @GET("discover/tv")
//    suspend fun discoverTvShows(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String,
//        @Query("sort_by") type: SortTypeParam,
//        @Query("with_genres") genres: GenresParam,
//        @Query("with_watch_providers") watchProviders: WatchProvidersParam,
//        @Query("watch_region") watchRegion: String = region,
//        @FloatRange(from = 0.0)
//        @Query("vote_average.gte")
//        voteAverageMin: Float,
//        @FloatRange(from = 0.0)
//        @Query("vote_average.lte")
//        voteAverageMax: Float,
//        @Query("first_air_date.gte")
//        fromAirDate: DateParam?,
//        @Query("first_air_date.lte")
//        toAirDate: DateParam?
//    ): TvShowsResponse
//
//    @GET("tv/top_rated")
//    suspend fun getTopRatedTvShows(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): TvShowsResponse
//
//    @GET("tv/on_the_air")
//    suspend fun getOnTheAirTvShows(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): TvShowsResponse
//
//    @GET("tv/popular")
//    suspend fun getPopularTvShows(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): TvShowsResponse
//
//    @GET("tv/airing_today")
//    suspend fun getAiringTodayTvShows(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): TvShowsResponse
//
//    @GET("tv/{tv_id}")
//    fun getTvShowDetails(
//        @Path("tv_id") tvShowId: Int,
//        @Query("language") isoCode: String
//    ): Call<TvShowDetails>
//
//    @GET("tv/{tv_id}/similar")
//    suspend fun getSimilarTvShows(
//        @Path("tv_id") tvShowId: Int,
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): TvShowsResponse
//
//    @GET("tv/{tv_id}/recommendations")
//    suspend fun getTvShowsRecommendations(
//        @Path("tv_id") tvShowId: Int,
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): TvShowsResponse
//
//    @GET("tv/{tv_id}/season/{season_number}")
//    fun getTvSeasons(
//        @Path("tv_id") tvShowId: Int,
//        @Path("season_number") seasonNumber: Int,
//        @Query("language") isoCode: String
//    ): Call<TvSeasonsResponse>
//
//    @GET("trending/tv/week")
//    suspend fun getTrendingTvShows(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String
//    ): TvShowsResponse
//
//    @GET("tv/{tv_id}/season/{season_number}")
//    fun getSeasonDetails(
//        @Path("tv_id") tvShowId: Int,
//        @Path("season_number") seasonNumber: Int,
//        @Query("language") isoCode: String
//    ): Call<SeasonDetails>
//
//    @GET("tv/{tv_id}/season/{season_number}/aggregate_credits")
//    fun getSeasonCredits(
//        @Path("tv_id") tvShowId: Int,
//        @Path("season_number") seasonNumber: Int,
//        @Query("language") isoCode: String,
//    ): Call<AggregatedCredits>
//
//    @GET("tv/{tv_id}/images")
//    fun getTvShowImages(
//        @Path("tv_id") tvShowId: Int
//    ): Call<ImagesResponse>
//
//    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/images")
//    fun getEpisodeImages(
//        @Path("tv_id") tvShowId: Int,
//        @Path("season_number") seasonNumber: Int,
//        @Path("episode_number") episodeNumber: Int
//    ): Call<ImagesResponse>
//
//    @GET("tv/{tv_id}/reviews")
//    suspend fun getTvShowReviews(
//        @Path("tv_id") movieId: Int,
//        @Query("page") page: Int
//    ): ReviewsResponse
//
//    @GET("tv/{tv_id}/reviews")
//    fun getTvShowReview(
//        @Path("tv_id") tvShowId: Int
//    ): Call<ReviewsResponse>
//
//    @GET("genre/tv/list")
//    fun getTvShowsGenres(
//        @Query("language") isoCode: String
//    ): Call<GenresResponse>
//
//    @GET("tv/{tv_id}/watch/providers")
//    fun getTvShowWatchProviders(
//        @Path("tv_id") tvShowId: Int
//    ): Call<WatchProvidersResponse>
//
//    @GET("tv/{tv_id}/external_ids")
//    fun getTvShowExternalIds(
//        @Path("tv_id") tvShowId: Int,
//    ): Call<ExternalIds>
//
//    @GET("watch/providers/tv")
//    fun getAllTvShowsWatchProviders(
//        @Query("language") isoCode: String,
//        @Query("watch_region") region: String
//    ): Call<AllWatchProvidersResponse>
//
//    @GET("tv/{tv_id}/videos")
//    fun getTvShowVideos(
//        @Path("tv_id") tvShowId: Int,
//        @Query("language") isoCode: String
//    ): Call<VideosResponse>
//
//    @GET("tv/{tv_id}/season/{season_number}/videos")
//    fun getSeasonVideos(
//        @Path("tv_id") tvShowId: Int,
//        @Path("season_number") seasonNumber: Int,
//        @Query("language") isoCode: String
//    ): Call<VideosResponse>
//
//
//    @GET("search/multi")
//    suspend fun multiSearch(
//        @Query("page") page: Int,
//        @Query("language") isoCode: String,
//        @Query("region") region: String,
//        @Query("query") query: String,
//        @Query("year") year: Int?,
//        @Query("include_adult") includeAdult: Boolean,
//        @Query("primary_release_year") releaseYear: Int?
//    ): SearchResponse
//
//    @GET("collection/{collection_id}")
//    fun getCollection(
//        @Path("collection_id") collectionId: Int,
//        @Query("language") isoCode: String
//    ): Call<CollectionResponse>
//
//    @GET("person/{person_id}")
//    fun getPersonDetails(
//        @Path("person_id") personId: Int,
//        @Query("language") isoCode: String
//    ): Call<PersonDetails>
//
//    @GET("person/{person_id}/combined_credits")
//    fun getCombinedCredits(
//        @Path("person_id") personId: Int,
//        @Query("language") isoCode: String
//    ): Call<CombinedCredits>
//
//    @GET("person/{person_id}/external_ids")
//    fun getPersonExternalIds(
//        @Path("person_id") personId: Int,
//        @Query("language") isoCode: String
//    ): Call<ExternalIds>
//}