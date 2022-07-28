package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.tvshow.TvShowRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowReviewsCountUseCase
import javax.inject.Inject

class GetTvShowReviewsCountUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetTvShowReviewsCountUseCase {
    override suspend fun invoke(tvShowId: Int): ApiResponse<Int> {
        val response = tvShowRepository.tvShowReview(tvShowId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val reviewCount = response.data?.totalResults ?: 0
                ApiResponse.Success(reviewCount)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}