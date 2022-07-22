package com.example.movplayv3.data.api

import androidx.annotation.FloatRange
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbTvShowsApi {
    @GET("configuration")
    fun getConfig(): Call<Config>

    @GET("discover/tv")
    suspend fun discoverTvSeries(
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
}