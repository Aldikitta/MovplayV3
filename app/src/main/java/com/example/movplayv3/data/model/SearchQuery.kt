package com.example.movplayv3.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class SearchQuery(
    @PrimaryKey
    @ColumnInfo(index = true)
    val query: String,
    @ColumnInfo(name = "last_use_date")
    val lastUseDate: Date
)
