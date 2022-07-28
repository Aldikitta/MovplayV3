package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.ClearRecentlyBrowsedTvShowsUseCase
import javax.inject.Inject

class ClearRecentlyBrowsedTvShowsUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) : ClearRecentlyBrowsedTvShowsUseCase {
    override fun invoke() {
        return recentlyBrowsedRepository.clearRecentlyBrowsedTvShows()
    }
}