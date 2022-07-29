package com.example.movplayv3.di

import android.content.Context
import com.example.movplayv3.data.paging.ConfigDataSource
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper
import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideConfigDataSource(
        @ApplicationContext context: Context,
        externalScope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        apiOtherHelper: TmdbOthersApiHelper,
        apiMovieHelper: TmdbMoviesApiHelper,
        apiTvShowHelper: TmdbTvShowsApiHelper
    ): ConfigDataSource = ConfigDataSource(
        context = context,
        externalScope = externalScope,
        defaultDispatcher = dispatcher,
        apiOtherHelper = apiOtherHelper,
        apiMovieHelper = apiMovieHelper,
        apiTvShowHelper = apiTvShowHelper
    )
}