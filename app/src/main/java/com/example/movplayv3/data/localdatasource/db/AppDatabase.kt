package com.example.movplayv3.data.localdatasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
//import com.example.movplayv3.data.localdatasource.db.movie.FavoritesMoviesDao
//import com.example.movplayv3.model.SearchQuery
//import com.example.movplayv3.model.movie.*
//import com.example.movplayv3.model.tvshow.*
import com.example.movplayv3.utils.DateConverters

//@Database(
//    entities = [
//        MovieFavorite::class,
//        TvShowFavorite::class,
//        RecentlyBrowsedMovie::class,
//        RecentlyBrowsedTvShow::class,
//        SearchQuery::class,
//        MovieEntity::class,
//        TvShowEntity::class,
//        MoviesRemoteKeys::class,
//        TvShowsRemoteKeys::class,
//        MovieDetailEntity::class,
//        TvShowDetailEntity::class,
//        MovieDetailsRemoteKey::class,
//        TvShowDetailsRemoteKey::class
//    ],
//    version = 1
//)
//@TypeConverters(DateConverters::class)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun favoritesMoviesDao(): FavoritesMoviesDao
//
//}