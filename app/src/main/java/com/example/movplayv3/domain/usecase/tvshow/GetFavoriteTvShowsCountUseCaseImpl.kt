package com.example.movplayv3.domain.usecase.interfaces.tvshow

import kotlinx.coroutines.flow.Flow

interface GetFavoriteTvShowsCountUseCaseImpl {
    operator fun invoke(): Flow<Int>
}