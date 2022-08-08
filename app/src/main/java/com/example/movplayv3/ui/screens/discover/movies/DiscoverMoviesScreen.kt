package com.example.movplayv3.ui.screens.discover.movies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movplayv3.data.model.SortOrder
import com.example.movplayv3.data.model.SortType
import com.example.movplayv3.ui.screens.destinations.MovieDetailsScreenDestination
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DiscoverMoviesScreen(
    viewModel: DiscoverMoviesViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val onBackClicked: () -> Unit = {navigator.navigateUp()}
    val onSortOrderChanged: (order: SortOrder) -> Unit = viewModel::onSortOrderChange
    val onSortTypeChanged: (type: SortType) -> Unit = viewModel::onSortTypeChange
    val onMovieClicked: (movieId: Int) -> Unit = { id ->
        val destination = MovieDetailsScreenDestination(
            movieId = id,
            startRoute = MovieScreenDestination.route
        )

        navigator.navigate(destination)
    }
    val onSaveFilterClicked: (state: MovieFilterState) -> Unit = viewModel::onFilterStateChange

    DiscoverMoviesScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onSortOrderChanged = onSortOrderChanged,
        onSortTypeChanged = onSortTypeChanged,
        onMovieClicked = onMovieClicked,
        onSaveFilterClicked = onSaveFilterClicked
    )
}

@Composable
fun DiscoverMoviesScreenContent(
    uiState: DiscoverMoviesScreenUIState,
    onBackClicked: () -> Unit,
    onSortOrderChanged: (order: SortOrder) -> Unit,
    onSortTypeChanged: (type: SortType) -> Unit,
    onMovieClicked: (movieId: Int) -> Unit,
    onSaveFilterClicked: (state: MovieFilterState) -> Unit
){
    val movies = uiState.movies.collectAsLazyPagingItems()
    val coroutineScope = rememberCoroutineScope()
//    val sheetState = remember
}