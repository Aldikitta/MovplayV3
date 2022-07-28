package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowExternalIdsUseCase
import javax.inject.Inject

class GetTvShowExternalIdsUseCaseImpl @Inject constructor() : GetTvShowExternalIdsUseCase {
    override suspend fun invoke(tvShowId: Int): ApiResponse<List<ExternalId>> {
        TODO("Not yet implemented")
    }

}