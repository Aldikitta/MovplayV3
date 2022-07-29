package com.example.movplayv3.utils

import androidx.navigation.NavController
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination

fun NavController.safeNavigate(
    route: String,
    onSameRouteSelected: (String) -> Unit = {}
) {
    val currentRoute = currentBackStackEntry?.destination?.route

    if (currentRoute == route) {
        onSameRouteSelected(route)
        return
    }

    val isInBackstack = backQueue.map { entry -> entry.destination.route }
        .any { it == route }

    if (isInBackstack) {
        popBackStack(
            route = route,
            inclusive = false
        )
    } else {
        navigate(route) {
            popUpTo(MovieScreenDestination.route)
        }
    }
}