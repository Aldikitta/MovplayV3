package com.example.movplayv3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class PersonDetails(
    val id: Int,
    val birthday: Date?,
    @Json(name = "deathday")
    val deathDate: Date?,
    @Json(name = "known_for_department")
    val knownFor: String,
    @Json(name = "also_knows_as")
    val alsoKnowsAs: List<String>?,
    val gender: Int,
    val name: String,
    val biography: String,
    val popularity: Float,
    @Json(name = "place_of_birth")
    val birthPlace: String?,
    @Json(name = "profile_path")
    val profilePath: String?,
    val adult: Boolean,
    val homepage: String?,
    @Json(name = "imdb_id")
    val imdbId: String?
)
