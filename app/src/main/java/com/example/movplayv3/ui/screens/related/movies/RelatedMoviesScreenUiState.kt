package com.example.movplayv3.ui.screens.related.movies

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.movplayv3.data.model.RelationType
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class RelatedMoviesScreenUiState(
    val relationType: RelationType,
    val movies: Flow<PagingData<Movie>>,
    val startRoute: String
) {
    companion object {
        fun getDefault(relationType: RelationType): RelatedMoviesScreenUiState {
            return RelatedMoviesScreenUiState(
                relationType = relationType,
                movies = emptyFlow(),
                startRoute = MovieScreenDestination.route
            )
        }
    }
}