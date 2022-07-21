package com.example.movplayv3.data.model.tvshow

import androidx.annotation.StringRes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class TvShowStatus(val value: String) {
    @Json(name = "Returning Series")
    Returning("Returning Series"),

    @Json(name = "Planned")
    Planned("Planned"),

    @Json(name = "In Production")
    InProduction("In Production"),

    @Json(name = "Ended")
    Ended("Ended"),

    @Json(name = "Canceled")
    Canceled("Canceled"),

    @Json(name = "Pilot")
    Pilot("Pilot");

    @StringRes
    fun getLabel(): Int = when (this) {
        Returning -> R.string.tv_series_status_returning_label
        Planned -> R.string.tv_series_status_planned_label
        InProduction -> R.string.tv_series_status_in_production_label
        Ended -> R.string.tv_series_status_ended_label
        Canceled -> R.string.tv_series_status_cancelled_label
        Pilot -> R.string.tv_series_status_pilot_label
    }
}