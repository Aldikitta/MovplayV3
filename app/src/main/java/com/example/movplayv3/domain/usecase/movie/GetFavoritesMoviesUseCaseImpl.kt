package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.movie.MovieFavorite
import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesMoviesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<PagingData<MovieFavorite>> {
        return favoritesRepository.favoriteMovies()
    }
}