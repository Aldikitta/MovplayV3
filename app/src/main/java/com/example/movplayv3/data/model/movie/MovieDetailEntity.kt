package com.example.movplayv3.data.model.movie

import androidx.room.Entity
import androidx.room.Index

@Entity(indices = [Index(value = ["language"])])
data class MovieDetailEntity(
    override val id: Int,

    )