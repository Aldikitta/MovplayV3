package com.example.movplayv3.ui.screens.reviews

import android.os.Parcelable
import com.example.movplayv3.data.model.MediaType
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewsScreenNavArgs(
    val startRoute: String,
    val mediaId: Int,
    val type: MediaType
) : Parcelable