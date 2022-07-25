package com.example.movplayv3.data.repository.browsed

import androidx.paging.PagingData
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.model.movie.RecentlyBrowsedMovie
import com.example.movplayv3.data.model.tvshow.RecentlyBrowsedTvShow
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import kotlinx.coroutines.flow.Flow

interface RecentlyBrowsedRepository {
    fun addRecentlyBrowsedMovie(movieDetails: MovieDetails)

    fun clearRecentlyBrowsedMovies()

    fun clearRecentlyBrowsedTvShows()
//
//    fun recentlyBrowsedMovies(): Flow<PagingData<RecentlyBrowsedMovie>>
//
//    fun addRecentlyBrowsedTvShows(tvShowDetails: TvShowDetails)
//
//    fun recentlyBrowsedTvShows(): Flow<PagingData<RecentlyBrowsedTvShow>>
}