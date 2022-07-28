package com.example.movplayv3.domain.usecase

import com.example.movplayv3.domain.usecase.interfaces.MediaSearchQueriesUseCase
import javax.inject.Inject

class MediaSearchQueriesUseCaseImpl @Inject constructor() : MediaSearchQueriesUseCase{
    override suspend fun invoke(query: String): List<String> {
        TODO("Not yet implemented")
    }
}