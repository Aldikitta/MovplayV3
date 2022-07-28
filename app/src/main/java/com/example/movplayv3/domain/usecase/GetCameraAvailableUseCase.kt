package com.example.movplayv3.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetCameraAvailableUseCase {
    operator fun invoke(): Flow<Boolean>
}