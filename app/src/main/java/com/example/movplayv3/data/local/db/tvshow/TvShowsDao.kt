package com.example.movplayv3.data.local.db.tvshow

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.movplayv3.data.model.tvshow.TvShowEntity
import com.example.movplayv3.data.model.tvshow.TvShowEntityType
import com.example.movplayv3.utils.TvShowEntityTypeConverters

@TypeConverters(TvShowEntityTypeConverters::class)
@Dao
interface TvShowsDao {
    @Query("SELECT * FROM TvShowEntity WHERE type=:type AND language=:language")
    fun getAllTvSeries(type: TvShowEntityType, language: String): PagingSource<Int, TvShowEntity>
}