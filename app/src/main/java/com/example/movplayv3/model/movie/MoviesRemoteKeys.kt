package com.example.movplayv3.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val language: String,
    val type: MovieEntityType,
    val nextPage: Int?,
    val lastUpdated: Long
)
