package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<MovieDetails>
}