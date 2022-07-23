package com.example.movplayv3.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Credits(
    val cast: List<CastMember>?,
    val crew: List<CrewMember>?
)
