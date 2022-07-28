package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetFavoriteTvShowsCountUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteTvShowsCountUseCaseImpl @Inject constructor() : GetFavoriteTvShowsCountUseCase {
    override fun invoke(): Flow<Int> {
        TODO("Not yet implemented")
    }
}