package com.example.movplayv3.ui.screens.tvshow

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movplayv3.MainViewModel
import com.example.movplayv3.R
import com.example.movplayv3.data.model.tvshow.TvShowType
import com.example.movplayv3.ui.components.sections.MovplayPresentableSection
import com.example.movplayv3.ui.components.sections.MovplayPresentableTopSection
import com.example.movplayv3.ui.screens.destinations.BrowseTvShowsScreenDestination
import com.example.movplayv3.ui.screens.destinations.DiscoverTvShowScreenDestination
import com.example.movplayv3.ui.screens.destinations.TvShowDetailsScreenDestination
import com.example.movplayv3.ui.screens.destinations.TvShowScreenDestination
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.isAnyRefreshing
import com.example.movplayv3.utils.isNotEmpty
import com.example.movplayv3.utils.refreshAll
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLifecycleComposeApi::class)
@Destination
@Composable
fun AnimatedVisibilityScope.TvShowScreen(
    mainViewModel: MainViewModel,
    viewModel: TvShowScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        mainViewModel.sameBottomBarRoute.collectLatest { sameRoute ->
            if (sameRoute == TvShowScreenDestination.route) {
                scrollState.animateScrollTo(0)
            }
        }
    }

    val onTvShowClicked = { tvShowId: Int ->
        val destination =
            TvShowDetailsScreenDestination(
                tvShowId = tvShowId,
                startRoute = TvShowScreenDestination.route
            )

        navigator.navigate(destination)
    }

    val onBrowseTvShowClicked: (TvShowType) -> Unit = { type ->
        navigator.navigate(BrowseTvShowsScreenDestination(type))
    }

    val onDiscoverTvShowClicked = {
        navigator.navigate(DiscoverTvShowScreenDestination)
    }
    TvShowsScreenContent(
        uiState = uiState,
        scrollState = scrollState,
        onTvShowClicked = onTvShowClicked,
        onBrowseTvShowClicked = onBrowseTvShowClicked,
        onDiscoverTvShowClicked = onDiscoverTvShowClicked
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun TvShowsScreenContent(
    uiState: TvShowScreenUIState,
    scrollState: ScrollState,
    onTvShowClicked: (tvShowId: Int) -> Unit,
    onBrowseTvShowClicked: (type: TvShowType) -> Unit,
    onDiscoverTvShowClicked: () -> Unit
) {
    val density = LocalDensity.current

    val topRatedLazyItems = uiState.tvShowsState.topRated.collectAsLazyPagingItems()
    val discoverLazyItems = uiState.tvShowsState.discover.collectAsLazyPagingItems()
    val onTheAirLazyItems = uiState.tvShowsState.onTheAir.collectAsLazyPagingItems()
    val trendingLazyItems = uiState.tvShowsState.trending.collectAsLazyPagingItems()
    val airingTodayLazyItems = uiState.tvShowsState.airingToday.collectAsLazyPagingItems()
    val favoritesLazyItems = uiState.favorites.collectAsLazyPagingItems()
    val recentlyBrowsedLazyItems = uiState.recentlyBrowsed.collectAsLazyPagingItems()

    var topSectionHeight: Float? by remember {
        mutableStateOf(null)
    }
    val appBarHeight = density.run { 56.dp.toPx() }
    val topSectionScrollLimitValue: Float? = topSectionHeight?.minus(appBarHeight)

    val isRefreshing by derivedStateOf {
        listOf(
            topRatedLazyItems,
            discoverLazyItems,
            onTheAirLazyItems,
            trendingLazyItems,
            airingTodayLazyItems,
        ).isAnyRefreshing()
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    val refreshAllPagingData = {
        listOf(
            topRatedLazyItems,
            discoverLazyItems,
            onTheAirLazyItems,
            trendingLazyItems,
            airingTodayLazyItems
        ).refreshAll()
    }

    LaunchedEffect(isRefreshing) {
        swipeRefreshState.isRefreshing = isRefreshing
    }

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize(),
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
                title = stringResource(R.string.now_airing_tv_series),
                state = onTheAirLazyItems,
                scrollState = scrollState,
                scrollValueLimit = topSectionScrollLimitValue,
                onPresentableClick = onTvShowClicked,
                onMoreClick = {
                    onBrowseTvShowClicked(TvShowType.OnTheAir)
                }
            )
            MovplayPresentableSection(
                title = stringResource(R.string.explore_tv_series),
                state = discoverLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = onDiscoverTvShowClicked
            )
            MovplayPresentableSection(
                title = stringResource(R.string.top_rated_tv_series),
                state = topRatedLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = {
                    onBrowseTvShowClicked(TvShowType.TopRated)
                }
            )
            MovplayPresentableSection(
                title = stringResource(R.string.trending_tv_series),
                state = trendingLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = {
                    onBrowseTvShowClicked(TvShowType.Trending)
                }
            )
            MovplayPresentableSection(
                title = stringResource(R.string.today_airing_tv_series),
                state = airingTodayLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = {
                    onBrowseTvShowClicked(TvShowType.AiringToday)
                }
            )
            if(favoritesLazyItems.isNotEmpty()){
                MovplayPresentableSection(
                    title = stringResource(R.string.favourites_tv_series),
                    state = favoritesLazyItems,
                    onPresentableClick = onTvShowClicked,
                    onMoreClick = {
                        onBrowseTvShowClicked(TvShowType.Favorite)
                    }
                )
            }
            if (recentlyBrowsedLazyItems.isNotEmpty()){
                MovplayPresentableSection(
                    title = stringResource(R.string.recently_browsed_tv_series),
                    state = recentlyBrowsedLazyItems,
                    onPresentableClick = onTvShowClicked,
                    onMoreClick = {
                        onBrowseTvShowClicked(TvShowType.RecentlyBrowsed)
                    }
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
    }
}