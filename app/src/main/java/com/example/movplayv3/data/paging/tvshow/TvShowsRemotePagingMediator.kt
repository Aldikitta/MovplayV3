package com.example.movplayv3.data.paging.tvshow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowEntity
import com.example.movplayv3.data.model.tvshow.TvShowEntityType
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper

@OptIn(ExperimentalPagingApi::class)
class TvShowsRemotePagingMediator(
    private val deviceLanguage: DeviceLanguage,
    private val type: TvShowEntityType,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, TvShowEntity>() {
}