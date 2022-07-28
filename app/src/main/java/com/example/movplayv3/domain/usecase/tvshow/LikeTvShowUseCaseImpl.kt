package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.LikeTvShowUseCase
import javax.inject.Inject

class LikeTvShowUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : LikeTvShowUseCase {
    override fun invoke(details: TvShowDetails) {
        return favoritesRepository.likeTvShow(details)
    }
}