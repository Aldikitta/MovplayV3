package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.repository.movie.MovieRepository
import javax.inject.Inject


class GetDiscoverMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) {

}