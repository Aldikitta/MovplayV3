package com.example.movplayv3.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.movplayv3.data.model.Presentable

@Entity(indices = [Index(value = ["type", "language"])])
data class MovieEntity(
    override val id: Int,
    val type: MovieEntityType,
    @ColumnInfo(name = "poster_path")
    override val posterPath: String?,
    override val title: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    val language: String
) : Presentable {
    @PrimaryKey(autoGenerate = true)
    val entityId: Int = 0
}

enum class MovieEntityType {
    Discover, Upcoming, Trending, TopRated, Popular
}
