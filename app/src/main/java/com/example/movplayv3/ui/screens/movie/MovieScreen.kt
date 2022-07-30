package com.example.movplayv3.ui.screens.movie

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movplayv3.MainViewModel
import com.example.movplayv3.R
import com.example.movplayv3.data.model.movie.MovieType
import com.example.movplayv3.ui.components.dialogs.MovplayExitDialog
import com.example.movplayv3.ui.components.sections.MovplayPresentableTopSection
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.isAnyRefreshing
import com.example.movplayv3.utils.refreshAll
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
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
        scrollState = scrollState,

        )
}

@SuppressLint("UnrememberedMutableState")
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

    val upcomingLazyItems = uiState.moviesState.upcoming.collectAsLazyPagingItems()
    val topRatedLazyItems = uiState.moviesState.topRated.collectAsLazyPagingItems()
    val trendingLazyItems = uiState.moviesState.trending.collectAsLazyPagingItems()
    val nowPlayingLazyItems = uiState.moviesState.nowPlaying.collectAsLazyPagingItems()
    val favoritesLazyItems = uiState.favorites.collectAsLazyPagingItems()

    var topSectionHeight: Float? by remember {
        mutableStateOf(null)
    }
    val appBarHeight = density.run { 56.dp.toPx() }
    val topSectionScrollLimitValue: Float? = topSectionHeight?.minus(appBarHeight)
    var showExitDialog by remember {
        mutableStateOf(false)
    }
    val dismissDialog = {
        showExitDialog = false
    }
    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        MovplayExitDialog(
            onDismissRequest = dismissDialog,
            onCancelClick = dismissDialog,
            onConfirmClick = {
                val activity = (context as? Activity)
                activity?.finish()
            }
        )
    }

    val isRefreshing by derivedStateOf {
        listOf(
            upcomingLazyItems,
            topRatedLazyItems,
            trendingLazyItems,
            nowPlayingLazyItems
        ).isAnyRefreshing()
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    val refreshAllPagingData = {
        listOf(
            upcomingLazyItems,
            topRatedLazyItems,
            trendingLazyItems,
            nowPlayingLazyItems
        ).refreshAll()
    }

    LaunchedEffect(isRefreshing) {
        swipeRefreshState.isRefreshing = isRefreshing
    }

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        state = swipeRefreshState,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = MaterialTheme.spacing.large),
                state = state,
                refreshTriggerDistance = trigger,
                fade = true,
                scale = true,
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            )
        },
        onRefresh = refreshAllPagingData
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            MovplayPresentableTopSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        topSectionHeight = coordinates.size.height.toFloat()
                    },
                title = stringResource(R.string.now_playing_movies),
                state = nowPlayingLazyItems,
                scrollState = scrollState,
                scrollValueLimit = topSectionScrollLimitValue,
//                onPresentableClick = onMovieClicked,
                onMoreClick = {
//                    onBrowseMoviesClicked(MovieType.NowPlaying)
                }
            )
        }
    }
}