package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.ExternalIds
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetPersonExternalIdsUseCaseImpl {
    suspend operator fun invoke(
        personId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<ExternalIds>
}