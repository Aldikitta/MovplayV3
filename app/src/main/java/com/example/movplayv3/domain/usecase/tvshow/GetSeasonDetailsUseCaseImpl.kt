package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.SeasonDetails
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.season.SeasonRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetSeasonDetailsUseCase
import javax.inject.Inject

class GetSeasonDetailsUseCaseImpl @Inject constructor(
    private val seasonRepository: SeasonRepository
) : GetSeasonDetailsUseCase {
    override suspend fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<SeasonDetails> {
        return seasonRepository.seasonDetails(
            tvShowId = tvShowId,
            seasonNumber = seasonNumber,
            deviceLanguage = deviceLanguage
        ).awaitApiResponse()
    }
}