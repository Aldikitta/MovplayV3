package com.example.movplayv3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @Json(name = "iso_639_1")
    val iso: String,
    val name: String
)
