package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetTvShowDetailsUseCase {
    suspend operator fun invoke(
        tvSeriesId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<TvShowDetails>
}