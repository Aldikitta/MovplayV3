package com.example.movplayv3.domain.usecase.interfaces.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.tvshow.TvShowFavorite
import kotlinx.coroutines.flow.Flow

interface GetFavoritesTvShowsUseCaseImpl {
    operator fun invoke(): Flow<PagingData<TvShowFavorite>>
}