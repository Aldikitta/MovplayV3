package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.example.movplayv3.data.model.DetailPresentable
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.MovieDetailEntity
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetNowPlayingMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetNowPlayingMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository

) : GetNowPlayingMoviesUseCase {
    override fun invoke(
        deviceLanguage: DeviceLanguage,
        filtered: Boolean
    ): Flow<PagingData<DetailPresentable>> {
        return movieRepository.nowPlayingMovies(deviceLanguage).mapLatest { data ->
            if (filtered) data.filterCompleteInfo() else data
        }.mapLatest { data -> data.map { it } }
    }

    private fun PagingData<MovieDetailEntity>.filterCompleteInfo(): PagingData<MovieDetailEntity> {
        return filter { movie ->
            movie.run {
                !backdropPath.isNullOrEmpty() &&
                        !posterPath.isNullOrEmpty() &&
                        title.isNotEmpty() &&
                        overview.isNotEmpty()
            }
        }
    }

}