package com.example.movplayv3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WatchProvidersResponse(
    val id: Int,
    val results: Map<String, WatchProviders>
)

@JsonClass(generateAdapter = true)
data class AllWatchProvidersResponse(
    val result: List<ProviderSource>
)

@JsonClass(generateAdapter = true)
data class WatchProviders(
    val link: String,

    val rent: List<ProviderSource>?,

    val buy: List<ProviderSource>?,

    val flatrate: List<ProviderSource>?
)

@JsonClass(generateAdapter = true)
data class ProviderSource(
    @Json(name = "display_priority")
    val displayPriority: Int,

    @Json(name = "logo_path")
    val logoPath: String,

    @Json(name = "provider_id")
    val providerId: Int,

    @Json(name = "provider_name")
    val providerName: String
)


