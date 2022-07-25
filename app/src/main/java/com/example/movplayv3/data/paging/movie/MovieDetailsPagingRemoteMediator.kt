package com.example.movplayv3.data.paging.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.MovieDetailEntity
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class MovieDetailsPagingRemoteMediator(
    private val deviceLanguage: DeviceLanguage,
    private val apiMovieHelper: TmdbMoviesApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, MovieDetailEntity>() {
    private val movieDetailsDao = appDatabase.moviesDetailsDao()
    private val movieDetailsRemoteKeysDao = appDatabase.moviesDetailsRemoteKeys()

    override suspend fun initialize(): InitializeAction {
        val remoteKey = appDatabase.withTransaction {
            movieDetailsRemoteKeysDao.getRemoteKey(deviceLanguage.languageCode)
        } ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)

        return if ((System.currentTimeMillis() - remoteKey.lastUpdates) >= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieDetailEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }

}