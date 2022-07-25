package com.example.movplayv3.data.paging.movie

import androidx.paging.RemoteMediator
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.MovieDetailEntity
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper

class MovieDetailsPagingRemoteMediator(
    private val deviceLanguage: DeviceLanguage,
    private val apiMovieHelper: TmdbMoviesApiHelper,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, MovieDetailEntity>() {

}