package com.example.movplayv3.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeasonInfo(
    val tvSeriesId: Int,
    val seasonNumber: Int
) : Parcelable
