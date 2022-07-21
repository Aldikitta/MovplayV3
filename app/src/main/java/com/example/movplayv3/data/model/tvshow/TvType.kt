package com.example.movplayv3.data.model.tvshow

import androidx.annotation.StringRes
import com.example.movplayv3.R
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class TvType(val value: String) {
    @Json(name = "Documentary")
    Documentary("Documentary"),

    @Json(name = "News")
    News("News"),

    @Json(name = "Miniseries")
    Miniseries("Miniseries"),

    @Json(name = "Reality")
    Reality("Reality"),

    @Json(name = "Scripted")
    Scripted("Scripted"),

    @Json(name = "Talk Show")
    TalkShow("Talk Show"),

    @Json(name = "Video")
    Video("Video");

    @StringRes
    fun getLabel(): Int = when (this) {
        Documentary -> R.string.tv_series_type_documentary_label
        News -> R.string.tv_series_type_news_label
        Miniseries -> R.string.tv_series_type_miniseries_label
        Reality -> R.string.tv_series_type_reality_label
        Scripted -> R.string.tv_series_type_scripted_label
        TalkShow -> R.string.tv_series_type_talk_show_label
        Video -> R.string.tv_series_type_video_label
    }
}