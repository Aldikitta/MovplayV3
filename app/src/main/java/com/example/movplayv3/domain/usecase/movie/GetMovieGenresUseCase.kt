package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.Genre
import kotlinx.coroutines.flow.Flow

interface GetMovieGenresUseCase {
    operator fun invoke(): Flow<List<Genre>>
}