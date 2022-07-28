package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.ProviderSource
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetAllTvShowsWatchProvidersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllTvShowsWatchProvidersUseCaseImpl @Inject constructor() :
    GetAllTvShowsWatchProvidersUseCase {
    override fun invoke(): Flow<List<ProviderSource>> {
        TODO("Not yet implemented")
    }
}