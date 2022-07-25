package com.example.movplayv3.data.repository.browsed

import com.example.movplayv3.data.local.db.movie.RecentlyBrowsedMoviesDao
import com.example.movplayv3.data.local.db.tvshow.RecentlyBrowsedTvShowsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecentlyBrowsedRepositoryImpl @Inject constructor(
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val recentlyBrowsedMoviesDao: RecentlyBrowsedMoviesDao,
    private val recentlyBrowsedTvSeriesDao: RecentlyBrowsedTvShowsDao
) {
}