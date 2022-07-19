package com.example.movplayv3.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AggregateCredits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)