package com.example.movplayv3.data.local.db.tvshow

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movplayv3.data.model.tvshow.RecentlyBrowsedTvShow

@Dao
interface RecentlyBrowsedTvShowsDao {
    @Query("SELECT * FROM RecentlyBrowsedTvShow ORDER BY added_date DESC")
    fun recentBrowsedTvSeries(): DataSource.Factory<Int, RecentlyBrowsedTvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecentlyBrowsedTvSeries(vararg recentlyBrowsedMovie: RecentlyBrowsedTvShow)

    @Query("SELECT COUNT(id) FROM RecentlyBrowsedTvShow")
    suspend fun recentlyBrowsedTvSeriesCount(): Int

    @Query("DELETE FROM RecentlyBrowsedTvShow WHERE id IN (SELECT id FROM RecentlyBrowsedTvShow ORDER BY added_date ASC LIMIT :limit)")
    suspend fun deleteLast(limit: Int = 1)
}