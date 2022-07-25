package com.example.movplayv3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movplayv3.data.model.Review
import com.example.movplayv3.data.model.ReviewsResponse
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.io.IOException

class ReviewsDataSource(
    private val mediaId: Int,
    private inline val apiHelperMethod: suspend (Int, Int) -> ReviewsResponse
) : PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        return try {
            val nextPage = params.key ?: 1
            val reviewsResponse = apiHelperMethod(mediaId, nextPage)

            val currentPage = reviewsResponse.page
            val totalPages = reviewsResponse.totalPages

            LoadResult.Page(
                data = reviewsResponse.results,
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