package com.example.movplayv3.domain.usecase.interfaces.tvshow

import kotlinx.coroutines.flow.Flow

interface GetFavoriteTvShowIdsUseCase {
    operator fun invoke(): Flow<List<Int>>
}