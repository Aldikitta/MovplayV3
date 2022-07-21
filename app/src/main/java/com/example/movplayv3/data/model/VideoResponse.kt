package com.example.movplayv3.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoResponse(
    val id: Int,
    val result: List<Video>
)

@JsonClass(generateAdapter = true)
data class Video(
    val id: String,
    @Json(name = "iso_639_1")
    val language: String,
    @Json(name = "iso_3166_1")
    val region: String,
)
