package com.example.movplayv3.data.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.data.model.movie.MoviesResponse
import javax.inject.Inject

class MovieDetailsResponsePagingDataSource @Inject constructor(
    private val movieId: Int,
    private val language: String = DeviceLanguage.default.languageCode,
    private val region: String = DeviceLanguage.default.region,
    private inline val apiHMovieHelperMethod: suspend (Int, Int, String, String) -> MoviesResponse
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        TODO("Not yet implemented")
    }
}