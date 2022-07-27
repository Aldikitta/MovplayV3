package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.remote.api.ApiResponse

interface GetMovieReviewsCountUseCase {
    suspend operator fun invoke(movieId: Int): ApiResponse<Int>
}