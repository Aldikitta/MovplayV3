package com.example.movplayv3.ui.screens.related.tvseries

import android.os.Parcelable
import com.example.movplayv3.data.model.RelationType
import kotlinx.parcelize.Parcelize

@Parcelize
data class RelatedTvShowScreenArgs(
    val tvShowId: Int,
    val type: RelationType,
    val startRoute: String
) : Parcelable