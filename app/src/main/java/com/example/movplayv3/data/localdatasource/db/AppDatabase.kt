package com.example.movplayv3.data.localdatasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movplayv3.data.model.movie.MovieDetailsRemoteKey
import com.example.movplayv3.data.model.movie.MovieFavourite

@Database(
    entities = [

    ],
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {

}