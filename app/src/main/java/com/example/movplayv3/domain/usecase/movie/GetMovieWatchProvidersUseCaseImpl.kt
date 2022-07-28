package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.WatchProviders
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetMovieWatchProvidersUseCase
import javax.inject.Inject

class GetMovieWatchProvidersUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieWatchProvidersUseCase {
    override suspend fun invoke(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<WatchProviders?> {
        val response = movieRepository.watchProviders(
            movieId = movieId
        ).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val results = response.data?.results
                val watchProviders = results?.getOrElse(deviceLanguage.region) { null }
                ApiResponse.Success(watchProviders)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }

}