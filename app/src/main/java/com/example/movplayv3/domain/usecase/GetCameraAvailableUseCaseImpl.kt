package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.domain.usecase.interfaces.GetCameraAvailableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCameraAvailableUseCaseImpl @Inject constructor(
    private val configRepository: ConfigRepository
) : GetCameraAvailableUseCase {
    override fun invoke(): Flow<Boolean> {
        return configRepository.getCameraAvailable()
    }
}