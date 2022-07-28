package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.ProviderSource
import kotlinx.coroutines.flow.Flow

interface GetAllTvShowsWatchProvidersUseCase {
    operator fun invoke(): Flow<List<ProviderSource>>
}