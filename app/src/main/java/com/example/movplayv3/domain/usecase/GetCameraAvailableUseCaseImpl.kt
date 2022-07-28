package com.example.movplayv3.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetCameraAvailableUseCaseImpl {
    operator fun invoke(): Flow<Boolean>
}