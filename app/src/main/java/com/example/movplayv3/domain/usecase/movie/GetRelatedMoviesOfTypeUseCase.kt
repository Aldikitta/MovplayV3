package com.example.movplayv3.domain.usecase.interfaces.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.RelationType
import com.example.movplayv3.data.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface GetRelatedMoviesOfTypeUseCase {
    operator fun invoke(
        movieId: Int,
        type: RelationType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>>
}