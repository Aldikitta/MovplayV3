package com.example.movplayv3.data.repository.season

import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeasonRepositoryImpl @Inject constructor(
    private val apiTvShowHelper: TmdbTvShowsApiHelper
): SeasonRepository {
    override fun getTvSeason(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): Call<TvSeasonsResponse> {
        return apiTvShowHelper.getTvSeasons(tvShowId, seasonNumber, deviceLanguage.languageCode)
    }

    override fun seasonDetails(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): Call<SeasonDetails> {
        return apiTvShowHelper.getSeasonDetails(tvShowId, seasonNumber, deviceLanguage.languageCode)
    }

    override fun episodesImage(
        tvShowId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Call<ImagesResponse> {
        return apiTvShowHelper.getEpisodeImages(tvShowId, seasonNumber, episodeNumber)
    }

    override fun seasonVideos(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<VideosResponse> {
        return apiTvShowHelper.getSeasonVideos(tvShowId, seasonNumber, isoCode)
    }

    override fun seasonCredits(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<AggregatedCredits> {
        return apiTvShowHelper.getSeasonCredits(tvShowId, seasonNumber, isoCode)
    }
}