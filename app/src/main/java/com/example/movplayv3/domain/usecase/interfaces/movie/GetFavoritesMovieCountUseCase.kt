package com.example.movplayv3.domain.usecase.interfaces.movie

import kotlinx.coroutines.flow.Flow

interface GetFavoritesMovieCountUseCase {
    operator fun invoke(): Flow<Int>
}