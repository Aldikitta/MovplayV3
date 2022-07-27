package com.example.movplayv3.domain.usecase.interfaces.tvshow

import kotlinx.coroutines.flow.Flow

interface GetFavoriteTvShowsCountUseCase {
    operator fun invoke(): Flow<Int>
}