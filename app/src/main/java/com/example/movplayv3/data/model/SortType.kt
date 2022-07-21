package com.example.movplayv3.data.model

import androidx.annotation.StringRes
import com.example.movplayv3.R

enum class SortType {
    Popularity, VoteCount, ReleaseDate, VoteAverage, OriginalTitle;

    fun toSortTypeParam(order: SortOrder) = when (this) {

    }

    @StringRes
    fun getLabelResId(): Int = when (this) {
        Popularity -> R.string.sort_type_popularity_label
        ReleaseDate -> R.string.sort_type_release_date_label
        VoteAverage -> R.string.sort_type_vote_average_label
        OriginalTitle -> R.string.sort_type_title_label
        VoteCount -> R.string.sort_type_vote_count_label
    }
}