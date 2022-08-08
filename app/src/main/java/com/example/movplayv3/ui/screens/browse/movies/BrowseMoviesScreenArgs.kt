package com.example.movplayv3.ui.screens.browse.movies

import android.os.Parcelable
import com.example.movplayv3.data.model.movie.MovieType
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrowseMoviesScreenArgs(
    val movieType: MovieType
) : Parcelable