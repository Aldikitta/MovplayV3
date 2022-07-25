package com.example.movplayv3.data.repository.browsed

import androidx.paging.PagingData
import com.example.movplayv3.data.local.db.movie.RecentlyBrowsedMoviesDao
import com.example.movplayv3.data.local.db.tvshow.RecentlyBrowsedTvShowsDao
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.model.movie.RecentlyBrowsedMovie
import com.example.movplayv3.data.model.tvshow.RecentlyBrowsedTvShow
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecentlyBrowsedRepositoryImpl @Inject constructor(
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val recentlyBrowsedMoviesDao: RecentlyBrowsedMoviesDao,
    private val recentlyBrowsedTvSeriesDao: RecentlyBrowsedTvShowsDao
) : RecentlyBrowsedRepository {
    private companion object {
        const val maxItems = 100
    }

    override fun addRecentlyBrowsedMovie(movieDetails: MovieDetails) {
        TODO("Not yet implemented")
    }

    override fun clearRecentlyBrowsedMovies() {
        TODO("Not yet implemented")
    }

    override fun clearRecentlyBrowsedTvShows() {
        TODO("Not yet implemented")
    }

    override fun recentlyBrowsedMovies(): Flow<PagingData<RecentlyBrowsedMovie>> {
        TODO("Not yet implemented")
    }

    override fun addRecentlyBrowsedTvShows(tvShowDetails: TvShowDetails) {
        TODO("Not yet implemented")
    }

    override fun recentlyBrowsedTvShows(): Flow<PagingData<RecentlyBrowsedTvShow>> {
        TODO("Not yet implemented")
    }
}