package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.ExternalContentType
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.tvshow.TvShowRepository
import javax.inject.Inject

class GetTvShowExternalIdsUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    suspend operator fun invoke(tvShowId: Int): ApiResponse<List<ExternalId>> {
        val response = tvShowRepository.getExternalIds(tvShowId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val externalIds = response.data?.toExternalIdList(type = ExternalContentType.Tv)
                ApiResponse.Success(externalIds)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}