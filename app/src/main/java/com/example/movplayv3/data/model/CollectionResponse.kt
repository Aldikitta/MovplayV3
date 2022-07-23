package com.example.movplayv3.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionResponse(
    val id: Int,
    val name: String,
    val overview: String?,
    val parts: List<Part>,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Transient
    val posterUrl: String? = null,
    @Transient
    val backdropUrl: String? = null
)