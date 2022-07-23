package com.example.movplayv3.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresResponse(
    val genres: List<Genre>
)