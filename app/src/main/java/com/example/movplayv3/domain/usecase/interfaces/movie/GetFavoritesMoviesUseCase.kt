package com.example.movplayv3.domain.usecase.interfaces.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.movie.MovieFavorite
import kotlinx.coroutines.flow.Flow

interface GetFavoritesMoviesUseCase {
    operator fun invoke(): Flow<PagingData<MovieFavorite>>
}