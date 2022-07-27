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
        tvSeriesId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): Call<TvSeasonsResponse> {
        return apiTvShowHelper.getTvSeasons(tvSeriesId, seasonNumber, deviceLanguage.languageCode)
    }

    override fun seasonDetails(
        tvSeriesId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): Call<SeasonDetails> {
        TODO("Not yet implemented")
    }

    override fun episodesImage(
        tvSeriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Call<ImagesResponse> {
        TODO("Not yet implemented")
    }

    override fun seasonVideos(
        tvSeriesId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<VideosResponse> {
        TODO("Not yet implemented")
    }

    override fun seasonCredits(
        tvSeriesId: Int,
        seasonNumber: Int,
        isoCode: String
    ): Call<AggregatedCredits> {
        TODO("Not yet implemented")
    }
}