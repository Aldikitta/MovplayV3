package com.example.movplayv3.data.model.tvshow

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["language"])])
data class TvShowDetailsRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val language: String,
    val nextPage: Int?,
    val lastUpdated: Long
)
