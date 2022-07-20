package com.example.movplayv3.data.model.movie

import androidx.room.Entity
import androidx.room.Index

@Entity(indices = [Index(value = ["type", "language"])])
data class MovieEntity(
    override val id: Int,
    val type: MovieEntityType,
)

enum class MovieEntityType {
    Discover, Upcoming, Trending, TopRated, Popular
}
