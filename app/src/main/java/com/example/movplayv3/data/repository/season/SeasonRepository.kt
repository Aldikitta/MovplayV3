package com.example.movplayv3.data.repository.season

import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
import retrofit2.Call

interface SeasonRepository {
    fun getTvSeason(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Call<TvSeasonsResponse>

    fun seasonDetails(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Call<SeasonDetails>

    fun episodesImage(
        tvShowId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Call<ImagesResponse>

    fun seasonVideos(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<VideosResponse>

    fun seasonCredits(
        tvShowId: Int,
        seasonNumber: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<AggregatedCredits>
}