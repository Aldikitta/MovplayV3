package com.example.movplayv3.data.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper

class DiscoverMoviesPagingDataSource(
    private val apiMovieHelper: TmdbMoviesApiHelper,
    private val deviceLanguage: DeviceLanguage,
    private val sortType: SortType = SortType.Popularity,
    private val sortOrder: SortOrder = SortOrder.Desc,
    private val genresParam: GenresParam = GenresParam(genres = emptyList()),
    private val watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
    private val voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
    private val onlyWithPosters: Boolean = false,
    private val onlyWithScore: Boolean = false,
    private val onlyWithOverview: Boolean = false,
    private val releaseDateRange: DateRange
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        TODO("Not yet implemented")
    }

}