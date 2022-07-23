package com.example.movplayv3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResult(
    override val id: Int,
    @Json(name = "name")
    val tvShowName: String?,
    @Json(name = "title")
    val movieTitle: String?,
    @Json(name = "media_type")
    val mediaType: MediaType,
    val overview: String?,
    @Json(name = "poster_path")
    override val posterPath: String?
) : Presentable {
    override val title: String = when {
        !movieTitle.isNullOrEmpty() -> movieTitle
        !tvShowName.isNullOrEmpty() -> tvShowName
        else -> ""
    }
}
