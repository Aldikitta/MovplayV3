package com.example.movplayv3.data.model.tvshow

import com.example.movplayv3.data.model.DetailPresentable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvShow(
    override val id: Int,
    @Json(name = "poster_path")
    override val posterPath: String?,
    override val overview: String,
    @Json(name = "first_air_date")
    val firstAirDate: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "origin_country")
    val originCountry: List<String>?,
    val name: String?,
    @Json(name = "backdrop_path")
    override val backdropPath: String?,
    val popularity: Float?,
    @Json(name = "vote_count")
    override val voteCount: Int,
    @Json(name = "vote_average")
    override val voteAverage: Float
) : DetailPresentable {
    override val adult: Boolean? = null
    override val title: String = name.orEmpty()
}
