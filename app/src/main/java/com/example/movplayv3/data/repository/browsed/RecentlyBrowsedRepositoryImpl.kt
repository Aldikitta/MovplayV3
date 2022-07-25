package com.example.movplayv3.data.repository.browsed

import androidx.paging.Pager
import androidx.paging.PagingConfig
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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecentlyBrowsedRepositoryImpl @Inject constructor(
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val recentlyBrowsedMoviesDao: RecentlyBrowsedMoviesDao,
    private val recentlyBrowsedTvShowsDao: RecentlyBrowsedTvShowsDao
) : RecentlyBrowsedRepository {
    private companion object {
        const val maxItems = 100
    }

    override fun addRecentlyBrowsedMovie(movieDetails: MovieDetails) {
        externalScope.launch(defaultDispatcher) {
            val recentlyBrowsedMovie = movieDetails.run {
                RecentlyBrowsedMovie(
                    id = id,
                    posterPath = posterPath,
                    title = title,
                    addedDate = Date()
                )
            }

            recentlyBrowsedMoviesDao.deleteAndAdd(
                recentlyBrowsedMovie,
                maxItems = maxItems
            )
        }
    }

    override fun clearRecentlyBrowsedMovies() {
        externalScope.launch(defaultDispatcher) {
            recentlyBrowsedMoviesDao.clear()
        }
    }

    override fun clearRecentlyBrowsedTvShows() {
        externalScope.launch(defaultDispatcher) {
            recentlyBrowsedTvShowsDao.clear()
        }
    }

    override fun recentlyBrowsedMovies(): Flow<PagingData<RecentlyBrowsedMovie>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        recentlyBrowsedMoviesDao.recentBrowsedMovie().asPagingSourceFactory()()
    }.flow.flowOn(defaultDispatcher)

    override fun addRecentlyBrowsedTvShows(tvShowDetails: TvShowDetails) {
        externalScope.launch(defaultDispatcher) {
            val recentlyBrowsedTvShow = tvShowDetails.run {
                RecentlyBrowsedTvShow(
                    id = id,
                    posterPath = posterPath,
                    name = title,
                    addedDate = Date()
                )
            }

            recentlyBrowsedTvShowsDao.deleteAndAdd(
                recentlyBrowsedTvShow,
                maxItems = maxItems
            )
        }
    }

    override fun recentlyBrowsedTvShows(): Flow<PagingData<RecentlyBrowsedTvShow>> {
        TODO("Not yet implemented")
    }
}