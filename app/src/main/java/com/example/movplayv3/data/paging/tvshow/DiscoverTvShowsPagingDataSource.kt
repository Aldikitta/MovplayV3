package com.example.movplayv3.data.paging.tvshow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvShow
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.io.IOException

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
        return try {
            val nextPage = params.key ?: 1

            val tvShowResponse = apiTvShowsHelper.discoverTvShows(
                page = nextPage,
                isoCode = deviceLanguage.languageCode,
                region = deviceLanguage.region,
                sortTypeParam = sortTypeParam,
                genresParam = genresParam,
                watchProvidersParam = watchProvidersParam,
                voteRange = voteRange,
                fromAirDate = fromAirDate,
                toAirDate = toAirDate
            )

            val currentPage = tvShowResponse.page
            val totalPages = tvShowResponse.totalPages

            LoadResult.Page(
                data = tvShowResponse.tvShows
                    .filter { tvShow ->
                        if (onlyWithPosters) !tvShow.posterPath.isNullOrEmpty() else true
                    }
                    .filter { tvShow ->
                        if (onlyWithScore) tvShow.voteCount > 0 && tvShow.voteAverage > 0f else true
                    }
                    .filter { tvShow ->
                        if (onlyWithOverview) tvShow.overview.isNotBlank() else true
                    },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (currentPage + 1 > totalPages) null else currentPage + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: JsonDataException) {
            FirebaseCrashlytics.getInstance().recordException(e)
            LoadResult.Error(e)
        }
    }
}