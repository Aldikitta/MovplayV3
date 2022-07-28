package com.example.movplayv3.domain.usecase.interfaces.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShow
import com.example.movplayv3.ui.screens.discover.movies.SortInfo
import com.example.movplayv3.ui.screens.discover.tvshows.TvShowFilterState
import kotlinx.coroutines.flow.FlowCollector

interface GetDiscoverTvShowsUseCase {
    operator fun invoke(
        sortInfo: SortInfo,
        filterState: TvShowFilterState,
        deviceLanguage: DeviceLanguage
    ): FlowCollector<PagingData<TvShow>>
}