package com.example.movplayv3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Collection(
    val id: Int,
    val name: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Transient
    val posterUrl: String? = null,
    @Transient
    val backdropUrl: String? = null
)