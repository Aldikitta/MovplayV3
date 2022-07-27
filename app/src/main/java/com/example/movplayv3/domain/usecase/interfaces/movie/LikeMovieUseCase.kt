package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.movie.MovieDetails

interface LikeMovieUseCase {
    operator fun invoke(details: MovieDetails)
}