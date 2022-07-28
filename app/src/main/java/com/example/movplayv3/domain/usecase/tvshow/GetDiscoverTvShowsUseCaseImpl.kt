package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.GenresParam
import com.example.movplayv3.data.model.WatchProvidersParam
import com.example.movplayv3.data.model.tvshow.TvShow
import com.example.movplayv3.data.repository.tvshow.TvShowRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetDiscoverTvShowsUseCase
import com.example.movplayv3.ui.screens.discover.movies.SortInfo
import com.example.movplayv3.ui.screens.discover.tvshows.TvShowFilterState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetDiscoverTvShowsUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetDiscoverTvShowsUseCase {
    override fun invoke(
        sortInfo: SortInfo,
        filterState: TvShowFilterState,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<TvShow>> {
        return tvShowRepository.discoverTvShows(
            deviceLanguage = deviceLanguage,
            sortType = sortInfo.sortType,
            sortOrder = sortInfo.sortOrder,
            genresParam = GenresParam(filterState.selectedGenres),
            watchProvidersParam = WatchProvidersParam(filterState.selectedWatchProviders),
            voteRange = filterState.voteRange.current,
            onlyWithPosters = filterState.showOnlyWithPoster,
            onlyWithScore = filterState.showOnlyWithScore,
            onlyWithOverview = filterState.showOnlyWithOverview,
            airDateRange = filterState.airDateRange
        )
    }
}