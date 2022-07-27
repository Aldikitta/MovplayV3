package com.example.movplayv3.domain.usecase.interfaces

import kotlinx.coroutines.flow.Flow

interface GetSpeechToTextAvailableUseCase {
    operator fun invoke(): Flow<Boolean>
}