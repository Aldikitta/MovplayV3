package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowReviewsCountUseCase
import javax.inject.Inject

class GetTvShowReviewsCountUseCaseImpl @Inject constructor() : GetTvShowReviewsCountUseCase{
    override suspend fun invoke(tvShowId: Int): ApiResponse<Int> {
        TODO("Not yet implemented")
    }

}