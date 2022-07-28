package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.AddRecentlyBrowsedTvShowUseCase
import javax.inject.Inject

class AddRecentlyBrowsedTvShowUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) : AddRecentlyBrowsedTvShowUseCase {
    override fun invoke(details: TvShowDetails) {
        return recentlyBrowsedRepository.addRecentlyBrowsedTvShows(details)
    }
}