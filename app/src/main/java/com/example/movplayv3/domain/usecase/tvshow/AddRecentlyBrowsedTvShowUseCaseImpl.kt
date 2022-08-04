package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import javax.inject.Inject

class AddRecentlyBrowsedTvShowUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke(details: TvShowDetails) {
        return recentlyBrowsedRepository.addRecentlyBrowsedTvShows(details)
    }
}