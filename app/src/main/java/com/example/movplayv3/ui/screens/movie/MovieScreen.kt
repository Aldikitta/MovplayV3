package com.example.movplayv3.ui.screens.movie

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movplayv3.MainViewModel
import com.example.movplayv3.data.model.movie.MovieType
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@RootNavGraph(start = true)
@Destination
@Composable
fun AnimatedVisibilityScope.MovieScreen(
    mainViewModel: MainViewModel,
    viewModel: MovieScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        mainViewModel.sameBottomBarRoute.collectLatest { sameRoute ->
            if (sameRoute == MovieScreenDestination.route) {
                scrollState.animateScrollTo(0)
            }
        }
    }
//    val onMovieClicked = { movieId: Int ->
//        val destination = MovieDetailsScreenDestination(
//            movieId = movieId,
//            startRoute = MoviesScreenDestination.route
//        )
//
//        navigator.navigate(destination)
//    }
//
//    val onBrowseMoviesClicked = { type: MovieType ->
//        navigator.navigate(BrowseMoviesScreenDestination(type))
//    }
//
//    val onDiscoverMoviesClicked = {
//        navigator.navigate(DiscoverMoviesScreenDestination)
//    }
    MoviesScreenContent(
        uiState = uiState,
        scrollState = scrollState
    )
}

@Composable
fun MoviesScreenContent(
    uiState: MovieScreenUIState,
    scrollState: ScrollState,
//    onMovieClicked: (movieId: Int) -> Unit,
//    onBrowseMoviesClicked: (type: MovieType) -> Unit,
//    onDiscoverMoviesClicked: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current


}