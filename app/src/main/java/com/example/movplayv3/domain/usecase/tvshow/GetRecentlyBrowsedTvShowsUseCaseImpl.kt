package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.tvshow.RecentlyBrowsedTvShow
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetRecentlyBrowsedTvShowsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentlyBrowsedTvShowsUseCaseImpl @Inject constructor() :
    GetRecentlyBrowsedTvShowsUseCase {
    override fun invoke(): Flow<PagingData<RecentlyBrowsedTvShow>> {
        TODO("Not yet implemented")
    }
}