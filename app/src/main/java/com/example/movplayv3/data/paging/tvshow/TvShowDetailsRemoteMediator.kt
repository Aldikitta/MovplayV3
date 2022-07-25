package com.example.movplayv3.data.paging.tvshow

import androidx.paging.*
import androidx.room.withTransaction
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowDetailEntity
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class TvShowDetailsRemoteMediator(
    private val deviceLanguage: DeviceLanguage,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, TvShowDetailEntity>() {
    private val tvShowDetailsDao = appDatabase.tvShowsDetailsDao()
    private val tvShowDetailsRemoteKeysDao = appDatabase.tvShowsDetailsRemoteKeys()

    override suspend fun initialize(): InitializeAction {
        val remoteKey = appDatabase.withTransaction {
            tvShowDetailsRemoteKeysDao.getRemoteKey(deviceLanguage.languageCode)
        } ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)

        return if ((System.currentTimeMillis() - remoteKey.lastUpdated) >= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvShowDetailEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}