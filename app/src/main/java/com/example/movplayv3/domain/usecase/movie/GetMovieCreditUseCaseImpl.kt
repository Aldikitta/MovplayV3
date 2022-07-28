package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetMovieCreditUseCase
import javax.inject.Inject

class GetMovieCreditUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieCreditUseCase {

}