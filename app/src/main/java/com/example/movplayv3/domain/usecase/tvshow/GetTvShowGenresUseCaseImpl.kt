package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.Genre
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowGenresUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTvShowGenresUseCaseImpl @Inject constructor() : GetTvShowGenresUseCase {
    override fun invoke(): Flow<List<Genre>> {
        TODO("Not yet implemented")
    }
}