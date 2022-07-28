package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.AggregatedCredits
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetSeasonCreditsUseCase
import javax.inject.Inject

class GetSeasonCreditsUseCaseImpl @Inject constructor() : GetSeasonCreditsUseCase {
    override suspend fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<AggregatedCredits> {
        TODO("Not yet implemented")
    }

}