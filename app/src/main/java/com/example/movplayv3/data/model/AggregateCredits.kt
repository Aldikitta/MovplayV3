package com.example.movplayv3.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AggregateCredits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)

@JsonClass(generateAdapter = true)
data class Cast(
    val adult: Boolean,
    val gender: Int?,
    override val id: Int,
    @Json(name = "known_for_department")
    val knownForDepartment: String?,
    val name: String,
    val order: Int,
    @Json(name = "original_name")
    val originalName: String,
    val popularity: Double,
    @Json(name = "profile_path")
    override val profilePath: String?,
    val roles: List<Role>,
    @Json(name = "total_episode_count")
    val totalEpisodeCount: Int
)
