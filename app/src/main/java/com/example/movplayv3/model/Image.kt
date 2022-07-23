package com.example.movplayv3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "aspect_ratio")
    val aspectRatio: Float,
    @Json(name = "file_path")
    val filePath: String,
    val height: Int,
    val width: Int,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "vote_count")
    val voteCount: Int
)
