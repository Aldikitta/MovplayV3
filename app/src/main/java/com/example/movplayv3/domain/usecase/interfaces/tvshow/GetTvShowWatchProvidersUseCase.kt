package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.WatchProviders
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetTvShowWatchProvidersUseCase {
    suspend operator fun invoke(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<WatchProviders?>
}