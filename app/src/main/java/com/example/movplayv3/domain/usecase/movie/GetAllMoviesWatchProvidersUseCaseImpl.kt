package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.ProviderSource
import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetAllMoviesWatchProvidersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoviesWatchProvidersUseCaseImpl @Inject constructor(
    private val configRepository: ConfigRepository
) : GetAllMoviesWatchProvidersUseCase {
    override fun invoke(): Flow<List<ProviderSource>> {
        return configRepository.getAllMoviesWatchProviders()
    }
}