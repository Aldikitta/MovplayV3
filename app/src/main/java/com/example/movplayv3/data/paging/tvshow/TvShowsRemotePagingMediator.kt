package com.example.movplayv3.data.paging.tvshow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowEntity
import com.example.movplayv3.data.model.tvshow.TvShowEntityType
import com.example.movplayv3.data.model.tvshow.TvShowsResponse
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper

@OptIn(ExperimentalPagingApi::class)
class TvShowsRemotePagingMediator(
    private val deviceLanguage: DeviceLanguage,
    private val type: TvShowEntityType,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, TvShowEntity>() {
    private val tvShowsDao = appDatabase.tvShowsDao()
    private val tvShowRemoteKeysDao = appDatabase.tvShowsRemoteKeysDao()

    private val apiHelperMethod: suspend (Int, String, String) -> TvShowsResponse = when (type) {
        TvShowEntityType.AiringToday -> apiTvShowHelper::getAiringTodayTvShows
        TvShowEntityType.TopRated -> apiTvShowHelper::getTopRatedTvShows
        TvShowEntityType.Trending -> apiTvShowHelper::getTrendingTvShows
        TvShowEntityType.Popular -> apiTvShowHelper::getPopularTvShows
        TvShowEntityType.Discover -> apiTvShowHelper::discoverTvShows
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvShowEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}