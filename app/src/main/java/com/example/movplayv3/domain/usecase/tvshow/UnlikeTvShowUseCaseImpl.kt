package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.UnlikeTvShowUseCase
import javax.inject.Inject

class UnlikeTvShowUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : UnlikeTvShowUseCase {
    override fun invoke(details: TvShowDetails) {
        return favoritesRepository.unlikeTvShows(details)
    }
}