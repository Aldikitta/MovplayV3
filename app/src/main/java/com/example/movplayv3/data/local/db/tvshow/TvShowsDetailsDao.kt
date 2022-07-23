package com.example.movplayv3.data.local.db.tvshow

import androidx.paging.PagingSource
import androidx.room.*
import com.example.movplayv3.data.model.tvshow.TvShowDetailEntity
import com.example.movplayv3.utils.TvShowEntityTypeConverters

@TypeConverters(TvShowEntityTypeConverters::class)
@Dao
interface TvShowsDetailsDao {
    @Query("SELECT * FROM TvShowDetailEntity WHERE language=:language")
    fun getAllTvSeries(language: String): PagingSource<Int, TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTvSeries(movies: List<TvShowDetailEntity>)

    @Query("DELETE FROM TvShowDetailEntity WHERE language=:language")
    suspend fun deleteAllTvSeries(language: String)
}