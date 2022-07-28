package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetFavoritesMovieCountUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFavoritesMovieCountUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : GetFavoritesMovieCountUseCase{
    override fun invoke(): Flow<Int> {
        return favoritesRepository.getFavoriteMoviesCount()
    }
}