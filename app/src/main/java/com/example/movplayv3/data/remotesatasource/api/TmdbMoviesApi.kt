package com.example.movplayv3.data.remotesatasource.api

import androidx.annotation.FloatRange
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.model.movie.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbMoviesApi {
    @GET("configuration")
    fun getConfig(): Call<Config>

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String,
        @Query("sort_by") type: SortTypeParam,
        @Query("with_genres") genres: GenresParam,
        @Query("with_watch_providers") watchProviders: WatchProvidersParam,
        @Query("watch_region") watchRegion: String = region,
        @FloatRange(from = 0.0)
        @Query("vote_average.gte")
        voteAverageMin: Float,
        @FloatRange(from = 0.0)
        @Query("vote_average.lte")
        voteAverageMax: Float,
        @Query("release_date.gte")
        fromReleaseDate: DateParam?,
        @Query("release_date.lte")
        toReleaseDate: DateParam?
    ): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): MoviesResponse

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") isoCode: String
    ): Call<MovieDetails>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") isoCode: String
    ): Call<Credits>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): MoviesResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMoviesRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): MoviesResponse

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): MoviesResponse

    @GET("movie/{movie_id}/images")
    fun getMovieImages(
        @Path("movie_id") movieId: Int
    ): Call<ImagesResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): ReviewsResponse

    @GET("movie/{movie_id}/reviews")
    fun getMovieReview(
        @Path("movie_id") movieId: Int
    ): Call<ReviewsResponse>

    @GET("genre/movie/list")
    fun getMovieGenres(
        @Query("language") isoCode: String
    ): Call<GenresResponse>

    @GET("movie/{movie_id}/watch/providers")
    fun getMovieWatchProviders(
        @Path("movie_id") movieId: Int
    ): Call<WatchProvidersResponse>

    @GET("movie/{movie_id}/external_ids")
    fun getMovieExternalIds(
        @Path("movie_id") movieId: Int,
    ): Call<ExternalIds>

    @GET("watch/providers/movie")
    fun getAllMoviesWatchProviders(
        @Query("language") isoCode: String,
        @Query("watch_region") region: String
    ): Call<AllWatchProvidersResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("language") isoCode: String
    ): Call<VideosResponse>

    @GET("discover/movie")
    suspend fun getOtherMoviesOfDirector(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String,
        @Query("with_crew") directorId: Int
    ): MoviesResponse
}