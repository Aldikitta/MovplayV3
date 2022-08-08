package com.example.movplayv3.ui.components.others

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SmartDisplay
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.R
import com.example.movplayv3.ui.screens.destinations.FavoriteScreenDestination
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import com.example.movplayv3.ui.screens.destinations.SearchScreenDestination
import com.example.movplayv3.ui.screens.destinations.TvShowScreenDestination

@Composable
fun MovplayBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: String? = null,
    backQueueRoutes: List<String?> = emptyList(),
    visible: Boolean = true,
    onItemClicked: (String) -> Unit = {}
) {
    val bottomBarRoutes = remember {
        mutableSetOf(
            MovieScreenDestination.route,
            TvShowScreenDestination.route,
            FavoriteScreenDestination.route,
            SearchScreenDestination.route
        )
    }

    val selectedRoute = when (currentRoute) {
        in bottomBarRoutes -> currentRoute
        else -> {
            backQueueRoutes.firstOrNull { route ->
                route in bottomBarRoutes
            } ?: MovieScreenDestination.route
        }
    }
    val paddingValues = WindowInsets.navigationBars.asPaddingValues()

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        NavigationBar(
            modifier = modifier.padding(paddingValues),
            ) {
            MovplayNavBarItem(
                selected = selectedRoute == MovieScreenDestination.route,
                onClick = {
                    onItemClicked(MovieScreenDestination.route)
                },
                label = stringResource(R.string.movies_label),
                selectedIcon = Icons.Filled.Movie,
                unSelectedIcon = Icons.Outlined.Movie,
                contentDescription = "Movie"
//                icon = {
//                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Movie")
//                }
            )
            MovplayNavBarItem(
                selected = selectedRoute == TvShowScreenDestination.route,
                onClick = {
                    onItemClicked(TvShowScreenDestination.route)
                },
                label = stringResource(R.string.tv_series_label),
                selectedIcon = Icons.Filled.SmartDisplay,
                unSelectedIcon = Icons.Outlined.SmartDisplay,
                contentDescription = "Tv Show"
//                icon = {
//                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Tv Show")
//                }
            )
            MovplayNavBarItem(
                selected = selectedRoute == FavoriteScreenDestination.route,
                onClick = {
                    onItemClicked(FavoriteScreenDestination.route)
                },
                label = stringResource(R.string.favourites_label),
                selectedIcon = Icons.Filled.Favorite,
                unSelectedIcon = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorites"
//                icon = {
//                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorites")
//                }
            )
            MovplayNavBarItem(
                selected = selectedRoute == SearchScreenDestination.route,
                onClick = {
                    onItemClicked(SearchScreenDestination.route)
                },
                label = stringResource(R.string.search_label),
                selectedIcon = Icons.Filled.ZoomIn,
                unSelectedIcon = Icons.Outlined.Search,
                contentDescription = "Search"
//                icon = {
//                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
//                }
            )
        }
    }
}

@Composable
fun RowScope.MovplayNavBarItem(
    label: String,
//    icon: @Composable () -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    selectedIcon: ImageVector,
    unSelectedIcon: ImageVector,
    contentDescription: String? = null,

    ) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        label = {
            Text(label)
        },
        icon = {
            Icon(
                imageVector = if (selected) selectedIcon else unSelectedIcon,
                contentDescription = contentDescription
            )
        }
    )
}