package com.example.movplayv3.domain.usecase.interfaces.tvshow

import kotlinx.coroutines.flow.Flow

interface GetFavoriteTvShowIdsUseCaseImpl {
    operator fun invoke(): Flow<List<Int>>
}