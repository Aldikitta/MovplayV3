package com.example.movplayv3.domain.usecase.interfaces.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.ui.screens.discover.movies.MovieFilterState
import com.example.movplayv3.ui.screens.discover.movies.SortInfo
import kotlinx.coroutines.flow.Flow

interface GetDiscoverMoviesUseCase {
    operator fun invoke(
        sortInfo: SortInfo,
        filterState: MovieFilterState,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>>
}