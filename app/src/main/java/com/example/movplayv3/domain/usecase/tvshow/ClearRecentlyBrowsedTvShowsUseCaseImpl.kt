package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import javax.inject.Inject

class ClearRecentlyBrowsedTvShowsUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke() {
        return recentlyBrowsedRepository.clearRecentlyBrowsedTvShows()
    }
}