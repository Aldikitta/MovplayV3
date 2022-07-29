package com.example.movplayv3.di

import android.content.Context
import androidx.room.Room
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.local.db.SearchQueryDao
import com.example.movplayv3.data.local.db.movie.*
import com.example.movplayv3.data.local.db.tvshow.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideFavoritesMoviesDao(database: AppDatabase): FavoritesMoviesDao =
        database.favoritesMoviesDao()

    @Provides
    fun provideFavoriteTvSeriesDao(database: AppDatabase): FavoritesTvShowsDao =
        database.favoritesTvShowDao()

    @Provides
    fun provideRecentlyBrowsedMoviesDao(database: AppDatabase): RecentlyBrowsedMoviesDao =
        database.recentlyBrowsedMovies()

    @Provides
    fun provideRecentlyBrowsedTvShowsDao(database: AppDatabase): RecentlyBrowsedTvShowsDao =
        database.recentlyBrowsedTvShows()

    @Provides
    fun provideSearchQueryDao(database: AppDatabase): SearchQueryDao =
        database.searchQueryDao()

    @Provides
    fun provideMoviesDao(database: AppDatabase): MoviesDao = database.moviesDao()

    @Provides
    fun provideMovieRemoteKeysDao(database: AppDatabase): MoviesRemoteKeysDao =
        database.moviesRemoteKeysDao()

    @Provides
    fun provideTvShowsDao(database: AppDatabase): TvShowsDao = database.tvShowsDao()

    @Provides
    fun provideTvShowsRemoteKeysDao(database: AppDatabase): TvShowsRemoteKeysDao =
        database.tvShowsRemoteKeysDao()

    @Provides
    fun provideMovieDetailsDao(database: AppDatabase): MoviesDetailsDao =
        database.moviesDetailsDao()

    @Provides
    fun provideMovieDetailsRemoteKeysDao(database: AppDatabase): MoviesRemoteKeysDao =
        database.moviesRemoteKeysDao()

    @Provides
    fun provideTvShowDetailsDao(database: AppDatabase): TvShowsDetailsDao =
        database.tvShowsDetailsDao()

    @Provides
    fun provideTbShowDetailsRemoteKeysDao(database: AppDatabase): TvShowsDetailsRemoteKeysDao =
        database.tvShowsDetailsRemoteKeys()
}