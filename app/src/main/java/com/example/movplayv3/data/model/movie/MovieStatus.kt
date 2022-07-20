package com.example.movplayv3.data.model.movie

import androidx.annotation.StringRes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
enum class MovieStatus(val value: String) {
    @Json(name = "Rumored")
    Rumored("Rumored"),

    @Json(name = "Planned")
    Planned("Planned"),

    @Json(name = "In Production")
    InProduction("In Production"),

    @Json(name = "Post Production")
    PostProduction("Post Production"),

    @Json(name = "Released")
    Released("Released"),

    @Json(name = "Cancelled")
    Cancelled("Cancelled");
    @StringRes


}