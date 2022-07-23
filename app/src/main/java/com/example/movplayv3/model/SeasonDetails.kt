package com.example.movplayv3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class SeasonDetails(
    @Json(name = "air_date")
    val airDate: Date?,
    val episodes: List<Episode>,
    val name: String,
    override val overview: String,
    override val id: Int,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "poster_path")
    override val posterPath: String?
) : DetailPresentable {
    override val adult: Boolean? = null
    override val backdropPath: String? = null
    override val voteAverage: Float = 0f
    override val voteCount: Int = 0
    override val title: String = name
}
