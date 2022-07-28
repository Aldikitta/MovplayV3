package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetMovieBackdropsUseCase
import javax.inject.Inject

class GetMovieBackdropsUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieBackdropsUseCase {
    override suspend fun invoke(movieId: Int): ApiResponse<List<Image>> {
        val response = movieRepository.movieImages(movieId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val backdrops = response.data?.backdrops ?: emptyList()
                ApiResponse.Success(backdrops)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}