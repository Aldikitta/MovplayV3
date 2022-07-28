package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.movie.GetMovieDetailsUseCase
import javax.inject.Inject

class GetMovieDetailsUseCaseImpl @Inject constructor(

) : GetMovieDetailsUseCase {
    override suspend fun invoke(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<MovieDetails> {
        TODO("Not yet implemented")
    }

}