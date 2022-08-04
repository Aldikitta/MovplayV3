package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.movie.RecentlyBrowsedMovie
import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentlyBrowsedMoviesUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository,
) {
    operator fun invoke(): Flow<PagingData<RecentlyBrowsedMovie>> {
        return recentlyBrowsedRepository.recentlyBrowsedMovies()
    }
}