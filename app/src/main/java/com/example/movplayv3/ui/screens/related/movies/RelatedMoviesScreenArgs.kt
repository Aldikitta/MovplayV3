package com.example.movplayv3.ui.screens.related.movies

import android.os.Parcelable
import com.example.movplayv3.data.model.RelationType
import kotlinx.parcelize.Parcelize

@Parcelize
data class RelatedMoviesScreenArgs(
    val movieId: Int,
    val type: RelationType,
    val startRoute: String
) : Parcelable