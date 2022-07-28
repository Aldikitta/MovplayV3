package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetFavoriteTvShowIdsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFavoriteTvShowIdsUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : GetFavoriteTvShowIdsUseCase {
    override fun invoke(): Flow<List<Int>> {
        return favoritesRepository.getFavoriteTvShowsIds()
    }
}