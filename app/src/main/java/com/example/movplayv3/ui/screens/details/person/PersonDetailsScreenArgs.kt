package com.example.movplayv3.ui.screens.details.person

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonDetailsScreenArgs(
    val personId: Int,
    val startRoute: String
) : Parcelable