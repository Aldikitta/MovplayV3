package com.example.movplayv3.data.local.db.tvshow

import androidx.paging.PagingSource
import androidx.room.*
import com.example.movplayv3.data.model.tvshow.TvShowEntity
import com.example.movplayv3.data.model.tvshow.TvShowEntityType
import com.example.movplayv3.utils.TvShowEntityTypeConverters

@TypeConverters(TvShowEntityTypeConverters::class)
@Dao
interface TvShowsDao {
    @Query("SELECT * FROM TvShowEntity WHERE type=:type AND language=:language")
    fun getAllTvShows(type: TvShowEntityType, language: String): PagingSource<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTvShow(movies: List<TvShowEntity>)

    @Query("DELETE FROM TvShowEntity WHERE type=:type AND language=:language")
    suspend fun deleteTvShowsOfType(type: TvShowEntityType, language: String)
}