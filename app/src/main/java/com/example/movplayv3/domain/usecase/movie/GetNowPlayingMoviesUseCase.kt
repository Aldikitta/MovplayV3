package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DetailPresentable
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetNowPlayingMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository

) : GetNowPlayingMoviesUseCase {
    override fun invoke(
        deviceLanguage: DeviceLanguage,
        filterId: Boolean
    ): Flow<PagingData<DetailPresentable>> {

    }

}