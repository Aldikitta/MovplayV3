package com.example.movplayv3.domain.usecase

import com.example.movplayv3.domain.usecase.interfaces.GetSpeechToTextAvailableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpeechToTextAvailableUseCaseImpl @Inject constructor() : GetSpeechToTextAvailableUseCase{
    override fun invoke(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}