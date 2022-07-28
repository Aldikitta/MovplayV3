package com.example.movplayv3.domain.usecase.interfaces.movie

import kotlinx.coroutines.flow.Flow

interface GetFavoriteMoviesIdsUseCase {
    operator fun invoke(): Flow<List<Int>>
}