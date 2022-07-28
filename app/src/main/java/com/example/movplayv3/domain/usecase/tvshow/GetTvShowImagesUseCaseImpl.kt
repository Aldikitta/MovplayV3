package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.tvshow.TvShowRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowImagesUseCase
import javax.inject.Inject

class GetTvShowImagesUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetTvShowImagesUseCase {
    override suspend fun invoke(tvShowId: Int): ApiResponse<List<Image>> {
        val response = tvShowRepository.tvShowImages(tvShowId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val images = response.data?.backdrops ?: emptyList()
                ApiResponse.Success(images)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}