package com.example.movplayv3.ui.screens.movie

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.movplayv3.data.model.movie.MovieFavorite
import com.example.movplayv3.data.model.movie.RecentlyBrowsedMovie
import kotlinx.coroutines.flow.Flow

@Stable
data class MovieScreenUIState(
    val favorites: Flow<PagingData<MovieFavorite>>
    val recentlyBrowsed: Flow<PagingData<RecentlyBrowsedMovie>>
) {
}

