package com.example.movplayv3.data.model.tvshow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvShowsResponse(
    val page: Int,
    @Json(name = "results")
    val tvShows: List<TvShow>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
