package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.domain.usecase.interfaces.GetSpeechToTextAvailableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpeechToTextAvailableUseCaseImpl @Inject constructor(
    private val configRepository: ConfigRepository
) : GetSpeechToTextAvailableUseCase{
    override fun invoke(): Flow<Boolean> {
        return configRepository.getSpeechToTextAvailable()
    }
}