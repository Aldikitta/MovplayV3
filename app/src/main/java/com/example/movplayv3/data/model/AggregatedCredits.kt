package com.example.movplayv3.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AggregatedCredits(
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
) : Member {
    override val firstLine: String = name
    override val secondLine: String = roles.joinToString(separator = ", ") { role ->
        role.character
    }
}

@JsonClass(generateAdapter = true)
data class Crew(
    val adult: Boolean,
    val department: String,
    val gender: Int?,
    override val id: Int,
    val jobs: List<Job>,
    @Json(name = "known_for_department")
    val knownForDepartment: String?,
    val name: String,
    @Json(name = "original_name")
    val originalName: String,
    val popularity: Double,
    @Json(name = "profile_path")
    override val profilePath: String?,
    @Json(name = "total_episode_count")
    val totalEpisodeCount: Int
) : Member {
    override val firstLine: String = name
    override val secondLine: String = jobs.joinToString(separator = ", ") { job ->
        job.job
    }
}

@JsonClass(generateAdapter = true)
data class Role(
    val character: String,
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "episode_count")
    val episodeCount: Int
)

@JsonClass(generateAdapter = true)
data class Job(
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "episode_count")
    val episodeCount: Int,
    val job: String
)