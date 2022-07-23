package com.example.movplayv3.data.model.tvshow

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["language", "type"])])
data class TvShowsRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val language: String,
    val type: TvShowEntityType,
    val nextPage: Int?,
    val lastUpdated: Long
)
