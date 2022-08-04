package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import javax.inject.Inject

class ClearRecentlyBrowsedMoviesUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke() {
        return recentlyBrowsedRepository.clearRecentlyBrowsedMovies()
    }
}