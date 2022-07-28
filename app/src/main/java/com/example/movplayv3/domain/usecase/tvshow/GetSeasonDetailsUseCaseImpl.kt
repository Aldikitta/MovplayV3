package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.SeasonDetails
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetSeasonDetailsUseCase
import javax.inject.Inject

class GetSeasonDetailsUseCaseImpl @Inject constructor() : GetSeasonDetailsUseCase {
    override suspend fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<SeasonDetails> {
        TODO("Not yet implemented")
    }

}