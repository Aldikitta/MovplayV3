package com.example.movplayv3.data.repository.tvshow

import androidx.paging.ExperimentalPagingApi
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class TvShowRepositoryImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val appDatabase: AppDatabase
) : TvShowRepository {
}