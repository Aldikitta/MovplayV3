package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.AddRecentlyBrowsedMovieUseCase
import javax.inject.Inject

class AddRecentlyBrowsedMovieUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) : AddRecentlyBrowsedMovieUseCase {
    override fun invoke(details: MovieDetails) {
        TODO("Not yet implemented")
    }
}
