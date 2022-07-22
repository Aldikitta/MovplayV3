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
        TODO("Not yet implemented")
    }

    override suspend fun getOnTheAirTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getAiringTodayTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        TODO("Not yet implemented")
    }

    override fun getTvShowDetails(tvShowId: Int, isoCode: String): Call<TvShowDetails> {
        TODO("Not yet implemented")
    }

    override suspend fun getSimilarTvShows(
        tvShowId: Int,
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getTvShowsRecommendations(
        tvShowId: Int,
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        TODO("Not yet implemented")
    }

    override fun getTvSeasons(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<TvSeasonsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingTvShows(
        page: Int,
        isoCode: String,
        region: String
    ): TvShowsResponse {
        TODO("Not yet implemented")
    }

    override fun getSeasonDetails(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<SeasonDetails> {
        TODO("Not yet implemented")
    }

    override fun getTvShowImages(tvShowId: Int): Call<ImagesResponse> {
        TODO("Not yet implemented")
    }

    override fun getEpisodeImages(
        tvShowId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Call<ImagesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getTvShowReviews(tvShowId: Int, page: Int): ReviewsResponse {
        TODO("Not yet implemented")
    }

    override fun getTvShowReview(tvShowId: Int): Call<ReviewsResponse> {
        TODO("Not yet implemented")
    }

    override fun getTvShowsGenres(isoCode: String): Call<GenresResponse> {
        TODO("Not yet implemented")
    }

    override fun getTvShowWatchProviders(tvShowId: Int): Call<WatchProvidersResponse> {
        TODO("Not yet implemented")
    }

    override fun getTvShowExternalIds(tvShowId: Int): Call<ExternalIds> {
        TODO("Not yet implemented")
    }

    override fun getAllTvShowsWatchProviders(
        isoCode: String,
        region: String
    ): Call<AllWatchProvidersResponse> {
        TODO("Not yet implemented")
    }

    override fun getTvShowVideos(tvShowId: Int, isoCode: String): Call<VideosResponse> {
        TODO("Not yet implemented")
    }

    override fun getSeasonVideos(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<VideosResponse> {
        TODO("Not yet implemented")
    }

    override fun getSeasonCredits(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<AggregatedCredits> {
        TODO("Not yet implemented")
    }
}