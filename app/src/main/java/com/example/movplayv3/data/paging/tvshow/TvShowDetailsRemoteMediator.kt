package com.example.movplayv3.data.paging.tvshow

import androidx.paging.*
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowDetailEntity
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper

@OptIn(ExperimentalPagingApi::class)
class TvShowDetailsRemoteMediator(
    private val deviceLanguage: DeviceLanguage,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, TvShowDetailEntity>() {
    private val tvShowDetailsDao = appDatabase.tvShowsDetailsDao()
    private val tvShowDetailsRemoteKeysDao = appDatabase.tvShowsDetailsRemoteKeys()

    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvShowDetailEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}