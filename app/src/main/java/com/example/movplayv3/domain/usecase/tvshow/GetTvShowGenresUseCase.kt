package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.Genre
import kotlinx.coroutines.flow.Flow

interface GetTvShowGenresUseCase {
    operator fun invoke(): Flow<List<Genre>>
}