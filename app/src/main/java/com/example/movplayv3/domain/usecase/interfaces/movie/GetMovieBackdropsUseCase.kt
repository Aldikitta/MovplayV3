package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetMovieBackdropsUseCase {
    suspend operator fun invoke(movieId: Int): ApiResponse<List<Image>>
}