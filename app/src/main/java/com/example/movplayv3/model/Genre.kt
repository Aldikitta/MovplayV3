package com.example.movplayv3.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Int,
    val name: String
)
