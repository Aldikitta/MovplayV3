package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetFavoriteMoviesIdsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesIdsUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : GetFavoriteMoviesIdsUseCase {
    override fun invoke(): Flow<List<Int>> {
        return favoritesRepository.getFavoriteMoviesIds()
    }
}