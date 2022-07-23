package com.example.movplayv3.data.localdatasource.db.movie

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movplayv3.model.movie.MovieFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesMoviesDao {
    @Query("SELECT * FROM MovieFavorite ORDER BY added_date DESC")
    fun favoriteMovies(): DataSource.Factory<Int, MovieFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeMovie(vararg movieDetails: MovieFavorite)

    @Query("DELETE FROM MovieFavorite WHERE id = :movieId")
    suspend fun unlikeMovie(movieId: Int)

    @Query("SELECT id FROM MovieFavorite")
    fun favouriteMoviesIds(): Flow<List<Int>>

    @Query("SELECT COUNT(id) FROM MovieFavorite")
    fun favouriteMoviesCount(): Flow<Int>
}