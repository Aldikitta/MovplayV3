package com.example.movplayv3.data.remote.api.tvshow

import androidx.annotation.FloatRange
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.model.tvshow.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbTvShowsApi {
    @GET("configuration")
    fun getConfig(): Call<Config>

    @GET("discover/tv")
    suspend fun discoverTvShows(
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
        @Query("first_air_date.gte")
        fromAirDate: DateParam?,
        @Query("first_air_date.lte")
        toAirDate: DateParam?
    ): TvShowsResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvShows(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/{tv_id}")
    fun getTvShowDetails(
        @Path("tv_id") tvShowId: Int,
        @Query("language") isoCode: String
    ): Call<TvShowDetails>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id") tvShowId: Int,
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/{tv_id}/recommendations")
    suspend fun getTvShowsRecommendations(
        @Path("tv_id") tvShowId: Int,
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/{tv_id}/season/{season_number}")
    fun getTvSeasons(
        @Path("tv_id") tvShowId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("language") isoCode: String
    ): Call<TvSeasonsResponse>

    @GET("trending/tv/week")
    suspend fun getTrendingTvShows(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/{tv_id}/season/{season_number}")
    fun getSeasonDetails(
        @Path("tv_id") tvShowId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("language") isoCode: String
    ): Call<SeasonDetails>

    @GET("tv/{tv_id}/season/{season_number}/aggregate_credits")
    fun getSeasonCredits(
        @Path("tv_id") tvShowId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("language") isoCode: String,
    ): Call<AggregatedCredits>

    @GET("tv/{tv_id}/images")
    fun getTvShowImages(
        @Path("tv_id") tvShowId: Int
    ): Call<ImagesResponse>

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/images")
    fun getEpisodeImages(
        @Path("tv_id") tvShowId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): Call<ImagesResponse>

    @GET("tv/{tv_id}/reviews")
    suspend fun getTvShowReviews(
        @Path("tv_id") movieId: Int,
        @Query("page") page: Int
    ): ReviewsResponse

    @GET("tv/{tv_id}/reviews")
    fun getTvShowReview(
        @Path("tv_id") tvShowId: Int
    ): Call<ReviewsResponse>

    @GET("genre/tv/list")
    fun getTvShowsGenres(
        @Query("language") isoCode: String
    ): Call<GenresResponse>

    @GET("tv/{tv_id}/watch/providers")
    fun getTvShowWatchProviders(
        @Path("tv_id") tvShowId: Int
    ): Call<WatchProvidersResponse>

    @GET("tv/{tv_id}/external_ids")
    fun getTvShowExternalIds(
        @Path("tv_id") tvShowId: Int,
    ): Call<ExternalIds>

    @GET("watch/providers/tv")
    fun getAllTvShowsWatchProviders(
        @Query("language") isoCode: String,
        @Query("watch_region") region: String
    ): Call<AllWatchProvidersResponse>

    @GET("tv/{tv_id}/videos")
    fun getTvShowVideos(
        @Path("tv_id") tvShowId: Int,
        @Query("language") isoCode: String
    ): Call<VideosResponse>

    @GET("tv/{tv_id}/season/{season_number}/videos")
    fun getSeasonVideos(
        @Path("tv_id") tvShowId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("language") isoCode: String
    ): Call<VideosResponse>
}