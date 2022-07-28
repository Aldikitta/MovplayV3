package com.example.movplayv3.domain.usecase.interfaces.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.movie.RecentlyBrowsedMovie
import kotlinx.coroutines.flow.Flow

interface GetRecentlyBrowsedMoviesUseCase {
    operator fun invoke(): Flow<PagingData<RecentlyBrowsedMovie>>
}