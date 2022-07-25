package com.example.movplayv3.data.paging.tvshow

import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper

class DiscoverTvShowsPagingDataSource(
    private val apiTvShowsHelper: TmdbTvShowsApiHelper,
    private val deviceLanguage: DeviceLanguage,
    private val sortType: SortType = SortType.Popularity,
    private val sortOrder: SortOrder = SortOrder.Desc,
    private val genresParam: GenresParam = GenresParam(genres = emptyList()),
    private val watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
    private val voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
    private val onlyWithPosters: Boolean = false,
    private val onlyWithScore: Boolean = false,
    private val onlyWithOverview: Boolean = false,
    private val airDateRange: DateRange
) {
}