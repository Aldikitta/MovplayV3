package com.example.movplayv3.model.movie

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["language"])])
data class MovieDetailsRemoteKey(
    @PrimaryKey(autoGenerate = true)
    val language: String,
    val nextPage: Int?,
    val lastUpdates: Long
)
