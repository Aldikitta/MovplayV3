package com.example.movplayv3.data.local.db.movie

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movplayv3.data.model.movie.RecentlyBrowsedMovie

@Dao
interface RecentlyBrowsedMoviesDao {
    @Query("SELECT * FROM RecentlyBrowsedMovie ORDER BY added_date DESC")
    fun recentBrowsedMovie(): DataSource.Factory<Int, RecentlyBrowsedMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecentlyBrowsedMovie(vararg recentlyBrowsedMovie: RecentlyBrowsedMovie)

    @Query("SELECT COUNT(id) FROM RecentlyBrowsedMovie")
    suspend fun recentlyBrowsedMovieCount(): Int

    @Query("DELETE FROM RecentlyBrowsedMovie WHERE id IN (SELECT id FROM RecentlyBrowsedMovie ORDER BY added_date ASC LIMIT :limit)")
    suspend fun deleteLast(limit: Int = 1)

    suspend fun deleteAndAdd(
        vararg recentlyBrowsedMovie: RecentlyBrowsedMovie,
        maxItems: Int = 100
    ) {
        val addCount = recentlyBrowsedMovie.count()
        val currentCount = recentlyBrowsedMovieCount()
        val removeCount = (currentCount + addCount - maxItems).coerceAtLeast(0)

        deleteLast(removeCount)
        addRecentlyBrowsedMovie(*recentlyBrowsedMovie)
    }

    @Query("DELETE FROM RecentlyBrowsedMovie")
    suspend fun clear()
}