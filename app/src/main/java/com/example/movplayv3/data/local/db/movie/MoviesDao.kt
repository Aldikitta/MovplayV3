package com.example.movplayv3.data.local.db.movie

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.movplayv3.data.model.movie.MovieEntity
import com.example.movplayv3.data.model.movie.MovieEntityType
import com.example.movplayv3.utils.MovieEntityTypeConverters

@TypeConverters(MovieEntityTypeConverters::class)
@Dao
interface MoviesDao {
    @Query("SELECT * FROM MovieEntity WHERE type=:type AND language=:language")
    fun getAllMovies(type: MovieEntityType, language: String): PagingSource<Int, MovieEntity>
}