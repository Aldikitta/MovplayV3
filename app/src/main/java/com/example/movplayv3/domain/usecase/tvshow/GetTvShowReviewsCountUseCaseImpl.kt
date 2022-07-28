package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.remote.api.ApiResponse

interface GetTvShowReviewsCountUseCaseImpl {
    suspend operator fun invoke(tvShowId: Int): ApiResponse<Int>
}