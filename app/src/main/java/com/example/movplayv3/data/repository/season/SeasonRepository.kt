package com.example.movplayv3.data.repository.season

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.ImagesResponse
import com.example.movplayv3.data.model.SeasonDetails
import com.example.movplayv3.data.model.tvshow.TvSeasonsResponse
import retrofit2.Call

interface SeasonRepository {
    fun getTvSeason(
        tvSeriesId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Call<TvSeasonsResponse>

    fun seasonDetails(
        tvSeriesId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Call<SeasonDetails>

    fun episodesImage(
        tvSeriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Call<ImagesResponse>
}