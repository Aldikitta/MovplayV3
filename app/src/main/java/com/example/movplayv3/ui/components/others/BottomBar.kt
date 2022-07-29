package com.example.movplayv3.ui.components.others

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.movplayv3.ui.screens.destinations.FavoriteScreenDestination
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import com.example.movplayv3.ui.screens.destinations.SearchScreenDestination
import com.example.movplayv3.ui.screens.destinations.TvShowScreenDestination

@Composable
fun BottomBar(
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

    val selectedRoutes = when (currentRoute) {
        in bottomBarRoutes -> currentRoute
        else -> {
            backQueueRoutes.firstOrNull { route ->
                route in bottomBarRoutes
            } ?: MovieScreenDestination.route
        }
    }
}