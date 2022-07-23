package com.example.movplayv3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Creator(
    override val id: Int,
    @Json(name = "credit_id")
    val creditId: String,
    val name: String,
    val gender: Int?,
    @Json(name = "profile_path")
    override val profilePath: String?
) : Member {
    override val firstLine: String = name
    override val secondLine: String? = null
}
