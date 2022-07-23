package com.example.movplayv3.model.tvshow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.movplayv3.data.model.Presentable

@Entity(indices = [Index(value = ["type", "language"])])
data class TvShowEntity(
    override val id: Int,
    val type: TvShowEntityType,
    @ColumnInfo(name = "poster_path")
    override val posterPath: String?,
    override val title: String,
    @ColumnInfo(name = "original_name")
    val originalName: String?,
    val language: String
) : Presentable {
    @PrimaryKey(autoGenerate = true)
    var entityId: Int = 0
}

enum class TvShowEntityType {
    Trending, TopRated, AiringToday, Popular, Discover
}
