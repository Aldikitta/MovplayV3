package com.example.movplayv3.data.repository.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvShow
import com.example.movplayv3.data.model.tvshow.TvShowEntity
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun discoverTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default,
        sortType: SortType = SortType.Popularity,
        sortOrder: SortOrder = SortOrder.Desc,
        genresParam: GenresParam = GenresParam(genres = emptyList()),
        watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
        voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
        onlyWithPosters: Boolean = false,
        onlyWithScore: Boolean = false,
        onlyWithOverview: Boolean = false,
        airDateRange: DateRange = DateRange()
    ): Flow<PagingData<TvShow>>

    fun topRatedTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>

    fun onTheAirTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>

    fun trendingTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>

    fun popularTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>

    fun airingTodayTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>
}