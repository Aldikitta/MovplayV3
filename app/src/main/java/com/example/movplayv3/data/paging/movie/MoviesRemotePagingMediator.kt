package com.example.movplayv3.data.paging.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.MovieEntity
import com.example.movplayv3.data.model.movie.MovieEntityType
import com.example.movplayv3.data.model.movie.MoviesResponse
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper

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

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}