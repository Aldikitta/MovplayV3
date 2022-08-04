package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.CombinedCredits
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.person.PersonRepository
import javax.inject.Inject

class GetCombinedCreditsUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(
        personId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<CombinedCredits> {
        return personRepository.getCombinedCredits(
            personId = personId,
            deviceLanguage = deviceLanguage
        ).awaitApiResponse()
    }
}