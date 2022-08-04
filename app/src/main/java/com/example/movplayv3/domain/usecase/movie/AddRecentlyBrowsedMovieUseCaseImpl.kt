package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import javax.inject.Inject

class AddRecentlyBrowsedMovieUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke(details: MovieDetails) {
        return recentlyBrowsedRepository.addRecentlyBrowsedMovie(details)
    }
}
