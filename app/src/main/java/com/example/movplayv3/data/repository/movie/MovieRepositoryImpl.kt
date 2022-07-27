package com.example.movplayv3.data.repository.movie

import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val apiMovieHelper: TmdbMoviesApiHelper,
    private val appDatabase: AppDatabase
) : MovieRepository {
}