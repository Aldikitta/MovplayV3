package com.example.movplayv3.data.api

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
    fun getTvShowsDetails(
        @Path("tv_id") tvSeriesId: Int,
        @Query("language") isoCode: String
    ): Call<TvShowDetails>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id") tvSeriesId: Int,
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/{tv_id}/recommendations")
    suspend fun getTvShowsRecommendations(
        @Path("tv_id") tvSeriesId: Int,
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): TvShowsResponse

    @GET("tv/{tv_id}/season/{season_number}")
    fun getTvSeasons(
        @Path("tv_id") tvSeriesId: Int,
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
        @Path("tv_id") tvSeriesId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("language") isoCode: String
    ): Call<SeasonDetails>

    @GET("tv/{tv_id}/season/{season_number}/aggregate_credits")
    fun getSeasonCredits(
        @Path("tv_id") tvSeriesId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("language") isoCode: String,
    ): Call<AggregatedCredits>

    @GET("tv/{tv_id}/images")
    fun getTvShowsImages(
        @Path("tv_id") tvSeriesId: Int
    ): Call<ImagesResponse>

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/images")
    fun getEpisodeImages(
        @Path("tv_id") tvSeriesId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): Call<ImagesResponse>
}