package com.example.movplayv3.ui.screens.browse.tvshows

import android.os.Parcelable
import com.example.movplayv3.data.model.tvshow.TvShowType
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrowseTvhowsScreenArgs(
    val tvShowType: TvShowType
) : Parcelable