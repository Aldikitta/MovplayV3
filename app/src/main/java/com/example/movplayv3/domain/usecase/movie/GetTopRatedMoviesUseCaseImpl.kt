package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import androidx.paging.map
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetTopRatedMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class GetTopRatedMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetTopRatedMoviesUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(deviceLanguage: DeviceLanguage): Flow<PagingData<Presentable>> {
        return movieRepository.topRatedMovies(deviceLanguage)
            .mapLatest { data -> data.map { it } }
    }
}