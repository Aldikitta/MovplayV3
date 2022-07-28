package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetFavoriteTvShowIdsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFavoriteTvShowIdsUseCaseImpl @Inject constructor() : GetFavoriteTvShowIdsUseCase {
    override fun invoke(): Flow<List<Int>> {
        TODO("Not yet implemented")
    }
}