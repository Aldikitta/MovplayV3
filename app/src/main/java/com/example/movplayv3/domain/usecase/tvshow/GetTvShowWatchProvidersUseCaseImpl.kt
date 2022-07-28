package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.WatchProviders
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowWatchProvidersUseCase
import javax.inject.Inject

class GetTvShowWatchProvidersUseCaseImpl @Inject constructor() : GetTvShowWatchProvidersUseCase {
    override suspend fun invoke(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<WatchProviders?> {
        TODO("Not yet implemented")
    }

}