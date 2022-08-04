package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.ExternalContentType
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.movie.MovieRepository
import javax.inject.Inject


class GetMovieExternalIdsUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): ApiResponse<List<ExternalId>> {
        val response = movieRepository.getExternalIds(movieId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val ids = response.data?.toExternalIdList(ExternalContentType.Movie)
                ApiResponse.Success(ids)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}