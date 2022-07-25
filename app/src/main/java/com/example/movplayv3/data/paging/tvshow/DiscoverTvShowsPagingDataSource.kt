package com.example.movplayv3.data.paging.tvshow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvShow
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
) : PagingSource<Int, TvShow>() {
    private val fromAirDate = airDateRange.from?.let(::DateParam)
    private val toAirDate = airDateRange.to?.let(::DateParam)
    private val sortTypeParam = sortType.toSortTypeParam(sortOrder)
    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        TODO("Not yet implemented")
    }
}