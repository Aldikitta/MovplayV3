package com.example.movplayv3.data.paging.tvshow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShow
import com.example.movplayv3.data.model.tvshow.TvShowsResponse
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.io.IOException

class TvShowDetailsResponsePagingDataSource(
    private val tvShowId: Int,
    private val deviceLanguage: DeviceLanguage,
    private inline val apiHelperMethod: suspend (Int, Int, String, String) -> TvShowsResponse
) : PagingSource<Int, TvShow>() {
    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        return try {
            val nextPage = params.key ?: 1
            val tvShowResponse = apiHelperMethod(
                tvShowId,
                nextPage,
                deviceLanguage.languageCode,
                deviceLanguage.region
            )

            val currentPage = tvShowResponse.page
            val totalPages = tvShowResponse.totalPages

            LoadResult.Page(
                data = tvShowResponse.tvShows,
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