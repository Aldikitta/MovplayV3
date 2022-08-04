package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import javax.inject.Inject

class UnlikeMovieUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(details: MovieDetails) {
        return favoritesRepository.unlikeMovie(details)
    }
}