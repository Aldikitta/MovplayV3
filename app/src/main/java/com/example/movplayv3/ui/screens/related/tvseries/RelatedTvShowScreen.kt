package com.example.movplayv3.ui.screens.related.tvseries

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
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movplayv3.R
import com.example.movplayv3.data.model.RelationType
import com.example.movplayv3.ui.components.others.MovplayBasicAppBar
import com.example.movplayv3.ui.components.sections.MovplayPresentableGridSection
import com.example.movplayv3.ui.screens.destinations.TvShowDetailsScreenDestination
import com.example.movplayv3.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalLifecycleComposeApi::class)
@Destination(navArgsDelegate = RelatedTvShowScreenArgs::class)
@Composable
fun AnimatedVisibilityScope.RelatedTvShowScreen(
    viewModel: RelatedTvShowViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onBackButtonClicked: () -> Unit = { navigator.navigateUp() }
    val onCloseClicked: () -> Unit = {
        navigator.popBackStack(uiState.startRoute, inclusive = false)
    }
    val onTvShowClicked: (tvShowId: Int) -> Unit = { id ->
        val destination = TvShowDetailsScreenDestination(
            tvShowId = id,
            startRoute = uiState.startRoute
        )

        navigator.navigate(destination)
    }

    RelatedTvSeriesScreenContent(
        uiState = uiState,
        onBackButtonClicked = onBackButtonClicked,
        onCloseClicked = onCloseClicked,
        onTvShowClicked = onTvShowClicked
    )
}

@Composable
fun RelatedTvSeriesScreenContent(
    uiState: RelatedTvShowScreenUiState,
    onBackButtonClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    onTvShowClicked: (tvShowId: Int) -> Unit
) {
    val tvShow = uiState.tvShow.collectAsLazyPagingItems()

    val appbarTitle = stringResource(
        when (uiState.relationType) {
            RelationType.Similar -> R.string.related_tv_series_screen_similar_appbar_label
            RelationType.Recommended -> R.string.related_tv_series_screen_recommendations_appbar_label
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
            state = tvShow,
            onPresentableClick = onTvShowClicked
        )
    }
}