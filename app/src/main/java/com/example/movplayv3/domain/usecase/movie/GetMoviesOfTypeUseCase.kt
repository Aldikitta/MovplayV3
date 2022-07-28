package com.example.movplayv3.domain.usecase.interfaces.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.movie.MovieType
import kotlinx.coroutines.flow.Flow

interface GetMoviesOfTypeUseCase {
    operator fun invoke(
        type: MovieType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Presentable>>
}