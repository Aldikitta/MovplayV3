package com.example.movplayv3.ui.screens.favorite

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movplayv3.data.model.FavoriteType
import com.example.movplayv3.ui.components.selectors.MovplayFavoriteTypeSelector
import com.example.movplayv3.ui.screens.destinations.*
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.isNotEmpty
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AnimatedVisibilityScope.FavoriteScreen(
    viewModel: FavoritesScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val onFavoriteTypeSelected: (type: FavoriteType) -> Unit = viewModel::onFavoriteTypeSelected
    val onFavoriteClicked: (favoriteId: Int) -> Unit = { id ->
        val destination = when (uiState.selectedFavouriteType) {
            FavoriteType.Movie -> {
                MovieDetailsScreenDestination(
                    movieId = id,
                    startRoute = FavoriteScreenDestination.route
                )
            }

            FavoriteType.TvShow -> {
                TvShowDetailsScreenDestination(
                    tvShowId = id,
                    startRoute = FavoriteScreenDestination.route
                )
            }
        }
        navigator.navigate(destination)
    }
    val onNavigateToMoviesButtonClicked: () -> Unit = {
        navigator.navigate(MovieScreenDestination) {
            popUpTo(MovieDetailsScreenDestination.route) {
                inclusive = true
            }
        }
    }
    val onNavigateToTvShowButtonClicked: () -> Unit = {
        navigator.navigate(TvShowScreenDestination) {
            popUpTo(MovieScreenDestination.route) {
                inclusive = true
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Favorites")
    }
    FavoriteScreenContent(
        uiState = uiState,
        onFavoriteTypeSelected = onFavoriteTypeSelected,
        onFavoriteClicked = onFavoriteClicked,
        onNavigateToMoviesButtonClicked = onNavigateToMoviesButtonClicked,
        onNavigateToTvShowButtonClicked = onNavigateToTvShowButtonClicked
    )
}

@Composable
fun FavoriteScreenContent(
    uiState: FavoritesScreenUIState,
    onFavoriteTypeSelected: (type: FavoriteType) -> Unit,
    onFavoriteClicked: (favouriteId: Int) -> Unit,
    onNavigateToMoviesButtonClicked: () -> Unit,
    onNavigateToTvShowButtonClicked: () -> Unit
) {
    val favoritesLazyItems = uiState.favorites.collectAsLazyPagingItems()
    val notEmpty = favoritesLazyItems.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        MovplayFavoriteTypeSelector(
            selected = uiState.selectedFavouriteType,
            onSelected = onFavoriteTypeSelected
        )
    }
}