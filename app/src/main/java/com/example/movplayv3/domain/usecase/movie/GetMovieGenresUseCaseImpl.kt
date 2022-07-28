package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.Genre
import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetMovieGenresUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMovieGenresUseCaseImpl @Inject constructor(
    private val configRepository: ConfigRepository
) : GetMovieGenresUseCase {
    override fun invoke(): Flow<List<Genre>> {
        return configRepository.getMoviesGenres()
    }
}