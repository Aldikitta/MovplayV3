package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.ClearRecentlyBrowsedMoviesUseCase
import javax.inject.Inject

class ClearRecentlyBrowsedMoviesUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
): ClearRecentlyBrowsedMoviesUseCase {
    override fun invoke() {
        return recentlyBrowsedRepository.clearRecentlyBrowsedMovies()
    }
}