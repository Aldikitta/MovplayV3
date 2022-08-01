package com.example.movplayv3.ui.screens.tvshow

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.movplayv3.data.model.DetailPresentable
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.tvshow.RecentlyBrowsedTvShow
import com.example.movplayv3.data.model.tvshow.TvShowFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class TvShowScreenUIState(
    val tvShowsState: TvShowsState,
    val favorites: Flow<PagingData<TvShowFavorite>>,
    val recentlyBrowsed: Flow<PagingData<RecentlyBrowsedTvShow>>
) {
    companion object {
        val default: TvShowScreenUIState = TvShowScreenUIState(
            tvShowsState = TvShowsState.default,
            favorites = emptyFlow(),
            recentlyBrowsed = emptyFlow()
        )
    }
}

@Stable
data class TvShowsState(
    val onTheAir: Flow<PagingData<DetailPresentable>>,
    val discover: Flow<PagingData<Presentable>>,
    val topRated: Flow<PagingData<Presentable>>,
    val trending: Flow<PagingData<Presentable>>,
    val airingToday: Flow<PagingData<Presentable>>
) {
    companion object {
        val default: TvShowsState = TvShowsState(
            onTheAir = emptyFlow(),
            discover = emptyFlow(),
            topRated = emptyFlow(),
            trending = emptyFlow(),
            airingToday = emptyFlow()
        )
    }
}