package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.ProviderSource
import kotlinx.coroutines.flow.Flow

interface GetAllMoviesWatchProvidersUseCase {
    operator fun invoke(): Flow<List<ProviderSource>>
}