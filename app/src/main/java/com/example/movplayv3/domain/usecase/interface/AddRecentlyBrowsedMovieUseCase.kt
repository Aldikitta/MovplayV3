package com.example.movplayv3.domain.usecase.`interface`

import com.example.movplayv3.data.model.movie.MovieDetails

interface AddRecentlyBrowsedMovieUseCase {
    operator fun invoke(details: MovieDetails)
}