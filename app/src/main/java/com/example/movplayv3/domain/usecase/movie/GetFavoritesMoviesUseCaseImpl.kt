package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetFavoritesMoviesUseCase
import javax.inject.Inject

class GetFavoritesMoviesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : GetFavoritesMoviesUseCase {
}