package com.example.movplayv3.di

import android.content.Context
import com.example.movplayv3.data.paging.ConfigDataSource
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper
import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepository
import com.example.movplayv3.data.repository.browsed.RecentlyBrowsedRepositoryImpl
import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.data.repository.config.ConfigRepositoryImpl
import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.data.repository.favorites.FavoritesRepositoryImpl
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.data.repository.movie.MovieRepositoryImpl
import com.example.movplayv3.data.repository.person.PersonRepository
import com.example.movplayv3.data.repository.person.PersonRepositoryImpl
import com.example.movplayv3.data.repository.search.SearchRepository
import com.example.movplayv3.data.repository.search.SearchRepositoryImpl
import com.example.movplayv3.data.repository.season.SeasonRepository
import com.example.movplayv3.data.repository.season.SeasonRepositoryImpl
import com.example.movplayv3.data.repository.tvshow.TvShowRepository
import com.example.movplayv3.data.repository.tvshow.TvShowRepositoryImpl
import dagger.Binds
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

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryBinds {
        @Binds
        @Singleton
        fun provideConfigRepository(impl: ConfigRepositoryImpl): ConfigRepository

        @Binds
        @Singleton
        fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

        @Binds
        @Singleton
        fun bindTvShowRepository(impl: TvShowRepositoryImpl): TvShowRepository

        @Binds
        @Singleton
        fun bindsBrowsedRepository(impl: RecentlyBrowsedRepositoryImpl): RecentlyBrowsedRepository

        @Binds
        @Singleton
        fun bindFavouritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository

        @Binds
        @Singleton
        fun bindPersonRepository(impl: PersonRepositoryImpl): PersonRepository

        @Binds
        @Singleton
        fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

        @Binds
        @Singleton
        fun bindSeasonRepository(impl: SeasonRepositoryImpl): SeasonRepository
    }
}