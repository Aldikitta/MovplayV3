package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetDiscoverAllMoviesUseCase
import javax.inject.Inject

class GetDiscoverAllMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetDiscoverAllMoviesUseCase {
}