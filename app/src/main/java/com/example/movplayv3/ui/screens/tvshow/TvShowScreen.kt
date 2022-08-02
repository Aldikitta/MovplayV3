package com.example.movplayv3.ui.screens.tvshow

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
import com.example.movplayv3.MainViewModel
import com.example.movplayv3.data.model.tvshow.TvShowType
import com.example.movplayv3.ui.screens.destinations.TvShowScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun TvShowScreen(
    mainViewModel: MainViewModel,
    viewModel: TvShowScreenViewModel,
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        mainViewModel.sameBottomBarRoute.collectLatest { sameRoute ->
            if (sameRoute == TvShowScreenDestination.route) {
                scrollState.animateScrollTo(0)
            }
        }
    }

//    val onTvSeriesClicked: (Int) -> Unit = { tvSeriesId ->
//        val destination = TvSeriesDetailsScreenDestination(
//            tvSeriesId = tvSeriesId,
//            startRoute = TvScreenDestination.route
//        )
//
//        navigator.navigate(destination)
//    }

//    val onBrowseTvSeriesClicked: (TvSeriesType) -> Unit = { type ->
//        navigator.navigate(BrowseTvSeriesScreenDestination(type))
//    }
//
//    val onDiscoverTvSeriesClicked = {
//        navigator.navigate(DiscoverTvSeriesScreenDestination)
//    }
}

@Composable
fun TvShowsScreenContent(
    uiState: TvShowScreenUIState,
    scrollState: ScrollState,
//    onTvSeriesClicked: (tvSeriesId: Int) -> Unit,
//    onBrowseTvSeriesClicked: (type: TvSeriesType) -> Unit,
    onDiscoverTvSeriesClicked: () -> Unit
) {
}