package com.example.movplayv3.domain.usecase

import com.example.movplayv3.domain.usecase.interfaces.GetCameraAvailableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCameraAvailableUseCaseImpl @Inject constructor() : GetCameraAvailableUseCase {
    override fun invoke(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}