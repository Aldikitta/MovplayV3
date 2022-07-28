package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetMovieExternalIdsUseCase {
    suspend operator fun invoke(movieId: Int): ApiResponse<List<ExternalId>>
}