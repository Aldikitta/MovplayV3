package com.example.movplayv3.data.repository.movie

import com.example.movplayv3.data.model.*

interface MovieRepository {
    fun discoverMovies(
        deviceLanguage: DeviceLanguage,
        sortType: SortType = SortType.Popularity,
        sortOrder: SortOrder = SortOrder.Desc,
        genresParam: GenresParam = GenresParam(genres = emptyList()),
        watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
        voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
        onlyWithPosters: Boolean = false,
        onlyWithScore: Boolean = false,
        onlyWithOverview: Boolean = false,
        releaseDateRange: DateRange = DateRange()
    )
}