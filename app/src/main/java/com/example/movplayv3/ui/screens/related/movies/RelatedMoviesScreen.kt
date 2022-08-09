package com.example.movplayv3.ui.screens.related.movies

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movplayv3.R
import com.example.movplayv3.data.model.RelationType
import com.example.movplayv3.ui.components.others.MovplayBasicAppBar
import com.example.movplayv3.ui.components.sections.MovplayPresentableGridSection
import com.example.movplayv3.ui.screens.destinations.MovieDetailsScreenDestination
import com.example.movplayv3.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = RelatedMoviesScreenArgs::class)
@Composable
fun AnimatedVisibilityScope.RelatedMoviesScreen(
    viewModel: RelatedMoviesViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val onBackButtonClicked: () -> Unit = { navigator.navigateUp() }
    val onCloseClicked: () -> Unit = {
        navigator.popBackStack(uiState.startRoute, inclusive = false)
    }
    val onMovieClicked: (movieId: Int) -> Unit = { id ->
        val destination = MovieDetailsScreenDestination(
            movieId = id,
            startRoute = uiState.startRoute
        )

        navigator.navigate(destination)
    }

    RelatedMoviesScreenContent(
        uiState = uiState,
        onBackButtonClicked = onBackButtonClicked,
        onCloseClicked,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun RelatedMoviesScreenContent(
    uiState: RelatedMoviesScreenUiState,
    onBackButtonClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    onMovieClicked: (movieId: Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    val appbarTitle = stringResource(
        when (uiState.relationType) {
            RelationType.Similar -> R.string.related_movies_screen_similar_appbar_label
            RelationType.Recommended -> R.string.related_movies_screen_recommendations_appbar_label
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        MovplayBasicAppBar(
            title = appbarTitle,
            action = {
                IconButton(onClick = onBackButtonClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "go back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
        )

        MovplayPresentableGridSection(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.large
            ),
            state = movies,
            onPresentableClick = onMovieClicked
        )
    }
}