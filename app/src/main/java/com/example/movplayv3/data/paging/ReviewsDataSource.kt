package com.example.movplayv3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movplayv3.data.model.Review
import com.example.movplayv3.data.model.ReviewsResponse

class ReviewsDataSource(
    private val mediaId: Int,
    private inline val apiHelperMethod: suspend (Int, Int) -> ReviewsResponse
): PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        TODO("Not yet implemented")
    }
}