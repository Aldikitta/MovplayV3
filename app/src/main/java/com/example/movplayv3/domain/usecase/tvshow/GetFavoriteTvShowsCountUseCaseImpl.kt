package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetFavoriteTvShowsCountUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteTvShowsCountUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : GetFavoriteTvShowsCountUseCase {
    override fun invoke(): Flow<Int> {
        return favoritesRepository.getFavoriteTvShowsCount()
    }
}