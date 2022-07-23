package com.example.movplayv3.data.local.db.movie

import androidx.room.Dao
import androidx.room.TypeConverters
import com.example.movplayv3.utils.MovieEntityTypeConverters

@TypeConverters(MovieEntityTypeConverters::class)
@Dao
interface MoviesDao {
}