package com.example.movplayv3.data.model

import androidx.annotation.StringRes
import com.example.movplayv3.R

enum class FavouriteType {
    Movie, TvShow;

    @StringRes
    fun getLabelResourceId() = when (this) {
        Movie -> R.string.favourite_type_movie_label
        TvShow -> R.string.favourite_type_tv_show_label
    }
}