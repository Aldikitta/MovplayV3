package com.example.movplayv3.data.local.db.tvshow

import androidx.room.Dao
import androidx.room.TypeConverters
import com.example.movplayv3.utils.TvShowEntityTypeConverters

@TypeConverters(TvShowEntityTypeConverters::class)
@Dao
interface TvShowsDetailsDao {
}