package com.example.movplayv3.ui.screens.browse.movies

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.movie.MovieType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class BrowseMoviesScreenUIState(
    val selectedMovieType: MovieType,
    val movies: Flow<PagingData<Presentable>>,
    val favoriteMoviesCount: Int
) {
    companion object {
        fun getDefault(selectedMovieType: MovieType): BrowseMoviesScreenUIState {
            return BrowseMoviesScreenUIState(
                selectedMovieType = selectedMovieType,
                movies = emptyFlow(),
                favoriteMoviesCount = 0
            )
        }
    }
}