package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.movie.MovieDetails

interface AddRecentlyBrowsedMovieUseCaseImpl {
    operator fun invoke(details: MovieDetails)
}
