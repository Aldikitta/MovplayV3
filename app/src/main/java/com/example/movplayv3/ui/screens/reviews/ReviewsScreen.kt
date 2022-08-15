package com.example.movplayv3.ui.screens.reviews

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.movplayv3.ui.components.lists.MovplayReviewsList
import com.example.movplayv3.ui.components.others.MovplayBasicAppBar
import com.example.movplayv3.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalLifecycleComposeApi::class)
@Destination(
    navArgsDelegate = ReviewsScreenNavArgs::class,
    style = ReviewsScreenTransitions::class
)
@Composable
fun AnimatedVisibilityScope.ReviewsScreen(
    viewModel: ReviewsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }
    val onCloseClicked: () -> Unit = {
        navigator.popBackStack(uiState.startRoute, inclusive = false)
    }

    ReviewsScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onCloseClicked = onCloseClicked
    )
}

@Composable
fun ReviewsScreenContent(
    uiState: ReviewsScreenUiState,
    onBackClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    val reviewsLazyItems = uiState.reviews.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        MovplayBasicAppBar(
            title = stringResource(R.string.reviews_screen_appbar_title),
            action = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "go back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
        )

        MovplayReviewsList(
            reviews = reviewsLazyItems,
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.large
            ),
            arrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
        )
    }
}