package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.movie.MovieDetails

interface LikeMovieUseCaseImpl {
    operator fun invoke(details: MovieDetails)
}