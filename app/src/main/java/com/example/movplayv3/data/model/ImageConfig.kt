package com.example.movplayv3.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageConfig(
    @Json(name = "base_url")
    val baseUrl: String,
    @Json(name = "secure_base_url")
    val secureBaseUrl: String,
    @Json(name = "backdrop_sizes")
    val backdropSizes: List<String>,
    @Json(name = "logo_sizes")
    val logoSizes: List<String>,
    @Json(name = "poster_sizes")
    val posterSizes: List<String>,
    @Json(name = "profile_sizes")
    val profileSizes: List<String>,
    @Json(name = "still_sizes")
    val stillSizes: List<String>
)
