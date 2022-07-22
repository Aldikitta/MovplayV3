package com.example.movplayv3.data.remotesatasource.api.tvshow

import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.model.tvshow.TvShowsResponse
import retrofit2.Call

interface TmdbTvShowsApiHelper {
    fun getConfig(): Call<Config>

    suspend fun discoverTvShows(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region,
        sortTypeParam: SortTypeParam = SortTypeParam.PopularityDesc,
        genresParam: GenresParam = GenresParam(genres = emptyList()),
        watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
        voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
        fromAirDate: DateParam? = null,
        toAirDate: DateParam? = null
    ): TvShowsResponse

    suspend fun getTopRatedTvShows(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): TvShowsResponse

    suspend fun getOnTheAirTvShows(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): TvShowsResponse

    suspend fun getPopularTvShows(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): TvShowsResponse

    suspend fun getAiringTodayTvShows(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): TvShowsResponse

    fun getTvShowsDetails(
        tvSeriesId: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<TvShowDetails>

    suspend fun getSimilarTvShows(
        tvSeriesId: Int,
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): TvShowsResponse

    suspend fun getTvShowsRecommendations(
        tvSeriesId: Int,
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): TvShowsResponse

    fun getTvSeasons(
        tvSeriesId: Int,
        seasonNumber: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<TvSeasonsResponse>

    suspend fun getTrendingTvSeries(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): TvShowsResponse

    fun getSeasonDetails(
        tvSeriesId: Int,
        seasonNumber: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<SeasonDetails>

    fun getTvSeriesImages(tvSeriesId: Int): Call<ImagesResponse>

    fun getEpisodeImages(
        tvSeriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Call<ImagesResponse>

    suspend fun getTvSeriesReviews(tvSeriesId: Int, page: Int): ReviewsResponse

    fun getTvSeriesReview(tvSeriesId: Int): Call<ReviewsResponse>

    fun getTvSeriesGenres(isoCode: String = DeviceLanguage.default.languageCode): Call<GenresResponse>

    fun getTvSeriesWatchProviders(
        tvSeriesId: Int
    ): Call<WatchProvidersResponse>

    fun getTvSeriesExternalIds(
        tvSeriesId: Int
    ): Call<ExternalIds>

    fun getAllTvSeriesWatchProviders(
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): Call<AllWatchProvidersResponse>
}