package com.example.movplayv3.domain.usecase.interfaces.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.tvshow.RecentlyBrowsedTvShow
import kotlinx.coroutines.flow.Flow

interface GetRecentlyBrowsedTvShowsUseCaseImpl {
    operator fun invoke(): Flow<PagingData<RecentlyBrowsedTvShow>>
}