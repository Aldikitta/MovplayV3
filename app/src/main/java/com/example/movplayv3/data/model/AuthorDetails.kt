package com.example.movplayv3.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDetails(
    val name: String,
    val username: String,
    @Json(name = "avatar_path")
    val avatarPath: String?,
    val rating: Float?,
    @Transient
    val avatarUrl: String? = null
)
