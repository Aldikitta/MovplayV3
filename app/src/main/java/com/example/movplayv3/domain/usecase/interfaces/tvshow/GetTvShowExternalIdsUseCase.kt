package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetTvShowExternalIdsUseCase {
    suspend operator fun invoke(tvSeriesId: Int): ApiResponse<List<ExternalId>>

}