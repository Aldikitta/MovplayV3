package com.example.movplayv3.data.remotesatasource.api.tvshow

import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.model.tvshow.TvShowsResponse
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbTvShowsApiHelperImpl @Inject constructor(
    private val tmdbTvShowsApi: TmdbTvShowsApi
) : TmdbTvShowsApiHelper {
    override fun getConfig(): Call<Config> {
        return tmdbTvShowsApi.getConfig()
    }

    override suspend fun discoverTvShows(
        page: Int,
        isoCode: String,
        region: String,
        sortTypeParam: SortTypeParam,
        genresParam: GenresParam,
        watchProvidersParam: WatchProvidersParam,
        voteRange: ClosedFloatingPointRange<Float>,
        fromAirDate: DateParam?,
        toAirDate: DateParam?
    ): TvShowsResponse {
        return tmdbTvShowsApi.discoverTvShows(
            page,
            isoCode,
            region,
            sortTypeParam,
            genresParam,
            watchProvidersParam,
            voteAverageMin = voteRange.start,
            voteAverageMax = voteRange.endInclusive,
            fromAirDate = fromAirDate,
            toAirDate = toAirDate
        )
    }

    override suspend fun getTopRatedTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        return tmdbTvShowsApi.getTopRatedTvShows(page, isoCode, region)
    }

    override suspend fun getOnTheAirTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        return tmdbTvShowsApi.getOnTheAirTvShows(page, isoCode, region)
    }

    override suspend fun getPopularTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        return tmdbTvShowsApi.getPopularTvShows(page, isoCode, region)
    }

    override suspend fun getAiringTodayTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        return tmdbTvShowsApi.getAiringTodayTvShows(page, isoCode, region)
    }

    override fun getTvShowDetails(tvShowId: Int, isoCode: String): Call<TvShowDetails> {
        return tmdbTvShowsApi.getTvShowDetails(tvShowId, isoCode)
    }

    override suspend fun getSimilarTvShows(
        tvShowId: Int,
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        return tmdbTvShowsApi.getSimilarTvShows(tvShowId, page, isoCode, region)
    }

    override suspend fun getTvShowsRecommendations(
        tvShowId: Int,
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        return tmdbTvShowsApi.getTvShowsRecommendations(tvShowId, page, isoCode, region)
    }

    override fun getTvSeasons(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<TvSeasonsResponse> {
        return tmdbTvShowsApi.getTvSeasons(tvShowId, seasonNumber, isoCode)
    }

    override suspend fun getTrendingTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        return tmdbTvShowsApi.getTrendingTvShows(page, isoCode, region)
    }

    override fun getSeasonDetails(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<SeasonDetails> {
        return tmdbTvShowsApi.getSeasonDetails(tvShowId, seasonNumber, isoCode)
    }

    override fun getTvShowImages(tvShowId: Int): Call<ImagesResponse> {
        return tmdbTvShowsApi.getTvShowImages(tvShowId)
    }

    override fun getEpisodeImages(
        tvShowId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Call<ImagesResponse> {
        return tmdbTvShowsApi.getEpisodeImages(tvShowId, seasonNumber, episodeNumber)
    }

    override suspend fun getTvShowReviews(tvShowId: Int, page: Int): ReviewsResponse {
//        return tmdbTvShowsApi.getTvShowReviews(tvShowId, page)
    }

    override fun getTvShowReview(tvShowId: Int): Call<ReviewsResponse> {
//        return tmdbTvShowsApi.getTvShowReview(tvShowId)
    }

    override fun getTvShowsGenres(isoCode: String): Call<GenresResponse> {
//        return tmdbTvShowsApi.getTvShowsGenres(isoCode)
    }

    override fun getTvShowWatchProviders(tvShowId: Int): Call<WatchProvidersResponse> {
//        return tmdbTvShowsApi.getTvShowWatchProviders(tvShowId)
    }

    override fun getTvShowExternalIds(tvShowId: Int): Call<ExternalIds> {
//        return tmdbTvShowsApi.getTvShowExternalIds(tvShowId)
    }

    override fun getAllTvShowsWatchProviders(
        isoCode: String,
        region: String
    ): Call<AllWatchProvidersResponse> {
//        return tmdbTvShowsApi.getAllTvShowsWatchProviders(isoCode, region)
    }

    override fun getTvShowVideos(tvShowId: Int, isoCode: String): Call<VideosResponse> {
//        return tmdbTvShowsApi.getTvShowVideos(tvShowId, isoCode)
    }

    override fun getSeasonVideos(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<VideosResponse> {
//        return tmdbTvShowsApi.getSeasonVideos(tvShowId, seasonNumber, isoCode)
    }

    override fun getSeasonCredits(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<AggregatedCredits> {
//        return tmdbTvShowsApi.getSeasonCredits(tvShowId, seasonNumber, isoCode)
    }
}