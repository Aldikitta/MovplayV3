package com.example.movplayv3.domain.usecase.interfaces.movie

import kotlinx.coroutines.flow.Flow

interface GetFavoriteMovieCountUseCase {
    operator fun invoke(): Flow<Int>
}