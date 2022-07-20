package com.example.movplayv3.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionResponse(
    val id: Int,
    val name: String,
    val overview: String?,
    val parts: List<Part>
)