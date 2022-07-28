package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.ExternalIds
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.GetPersonExternalIdsUseCase
import javax.inject.Inject

class GetPersonExternalIdsUseCaseImpl @Inject constructor() : GetPersonExternalIdsUseCase {
    override suspend fun invoke(
        personId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<ExternalIds> {
        TODO("Not yet implemented")
    }

}