package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.Genre
import com.example.movplayv3.data.repository.config.ConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTvShowGenresUseCaseImpl @Inject constructor(
    private val configRepository: ConfigRepository
) {
    operator fun invoke(): Flow<List<Genre>> {
        return configRepository.getTvShowGenres()
    }
}