package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.CombinedCredits
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.GetCombinedCreditsUseCase
import javax.inject.Inject

class GetCombinedCreditsUseCaseImpl @Inject constructor() : GetCombinedCreditsUseCase{
    override suspend fun invoke(
        personId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<CombinedCredits> {
        TODO("Not yet implemented")
    }

}