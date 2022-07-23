package com.example.movplayv3.model.tvshow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movplayv3.data.model.Presentable
import java.util.*

@Entity
data class TvShowFavorite(
    @PrimaryKey
    override val id: Int,
    @ColumnInfo(name = "poster_path")
    override val posterPath: String?,
    val name: String,
    @ColumnInfo(name = "added_date")
    val addedDate: Date
) : Presentable {
    @Transient
    override val title: String = name
}
