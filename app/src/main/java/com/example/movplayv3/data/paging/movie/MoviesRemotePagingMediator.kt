package com.example.movplayv3.data.paging.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.MovieEntity
import com.example.movplayv3.data.model.movie.MovieEntityType
import com.example.movplayv3.data.model.movie.MoviesResponse
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class MoviesRemotePagingMediator(
    private val deviceLanguage: DeviceLanguage,
    private val type: MovieEntityType,
    private val apiMovieHelper: TmdbMoviesApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, MovieEntity>() {
    private val movieCacheDao = appDatabase.moviesDao()
    private val remoteKeysDao = appDatabase.moviesRemoteKeysDao()

    private val apiMovieHelperMethod: suspend (Int, String, String) -> MoviesResponse =
        when (type) {
            MovieEntityType.TopRated -> apiMovieHelper::getTopRatedMovies
            MovieEntityType.Discover -> apiMovieHelper::discoverMovies
            MovieEntityType.Trending -> apiMovieHelper::getTrendingMovies
            MovieEntityType.Upcoming -> apiMovieHelper::getUpcomingMovies
            MovieEntityType.Popular -> apiMovieHelper::getPopularMovies
        }

    override suspend fun initialize(): InitializeAction {
        val remoteKey = appDatabase.withTransaction {
            remoteKeysDao.getRemoteKey(
                type = type,
                language = deviceLanguage.languageCode
            )
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
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}