package com.example.movplayv3.data.localdatasource.db.movie

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movplayv3.data.model.movie.MovieFavorite

@Dao
interface FavoritesMoviesDao {
    @Query("SELECT * FROM MovieFavorite ORDER BY added_date DESC")
    fun favoriteMovies(): DataSource.Factory<Int, MovieFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeMovie(vararg movieDetails: MovieFavorite)
}