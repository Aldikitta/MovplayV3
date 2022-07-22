package com.example.movplayv3.data.localdatasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movplayv3.data.model.SearchQuery
import com.example.movplayv3.data.model.movie.*
import com.example.movplayv3.data.model.tvshow.*
import com.example.movplayv3.utils.DateConverters

@Database(
    entities = [
        MovieFavourite::class,
        TvShowFavourite::class,
        RecentlyBrowsedMovie::class,
        RecentlyBrowsedTvShow::class,
        SearchQuery::class,
        MovieEntity::class,
        MoviesRemoteKeys::class,
        TvShowEntity::class,
        TvShowsRemoteKeys::class,
        MovieDetailEntity::class,
        MovieDetailsRemoteKey::class,
        TvShowDetailEntity::class,
        TvShowDetailsRemoteKey::class
    ],
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {

}