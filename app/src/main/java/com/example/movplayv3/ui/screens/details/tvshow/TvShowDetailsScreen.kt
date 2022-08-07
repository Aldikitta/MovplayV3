package com.example.movplayv3.ui.screens.details.tvshow

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.model.ShareDetails
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.ui.components.dialogs.MovplayErrorDialog
import com.example.movplayv3.ui.components.sections.MovplayExternalIdsSection
import com.example.movplayv3.ui.components.sections.MovplayPresentableDetailsTopSection
import com.example.movplayv3.ui.screens.destinations.TvShowDetailsScreenDestination
import com.example.movplayv3.ui.screens.details.components.MovplayTvShowDetailsTopContent
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.openExternalId
import com.example.movplayv3.utils.openVideo
import com.example.movplayv3.utils.shareImdb
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination(
    navArgsDelegate = TvShowDetailsScreenArgs::class,
    style = TvShowDetailsScreenTransitions::class
)
@Composable
fun AnimatedVisibilityScope.TvShowDetailsScreen(
    viewModel: TvShowDetailsScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }
    val onFavoriteClicked: (details: TvShowDetails) -> Unit = { details ->
        if (uiState.additionalTvShowDetailsInfo.isFavorite) {
            viewModel.onUnlikeClick(details)
        } else {
            viewModel.onLikeClick(details)
        }
    }
    val onCloseClicked: () -> Unit = {
        navigator.popBackStack(uiState.startRoute, inclusive = false)
    }
    val onExternalIdClicked = { id: ExternalId ->
        openExternalId(
            context = context,
            externalId = id
        )
    }
    val onShareClicked = { details: ShareDetails ->
        shareImdb(
            context = context,
            details = details
        )
    }
    val onVideoClicked = { video: Video ->
        openVideo(
            context = context,
            video = video
        )
    }
//    val onCreatorClicked = { personId: Int ->
//        val destination = PersonDetailsScreenDestination(
//            personId = personId,
//            startRoute = uiState.startRoute
//        )
//
//        navigator.navigate(destination)
//    }
    val onTvShowClicked = { tvShowId: Int ->
        val destination =
            TvShowDetailsScreenDestination(
                tvShowId = tvShowId,
                startRoute = uiState.startRoute
            )

        navigator.navigate(destination)
    }
//    val onReviewsClicked: () -> Unit = {
//        val tvSeriesId = uiState.tvSeriesDetails?.id
//
//        if (tvSeriesId != null) {
//            val destination = ReviewsScreenDestination(
//                startRoute = uiState.startRoute,
//                mediaId = tvSeriesId,
//                type = MediaType.Tv
//            )
//
//            navigator.navigate(destination)
//        }
//    }
//    val onSeasonClicked = { seasonNumber: Int ->
//        val tvSeriesId = uiState.tvSeriesDetails?.id
//
//        if (tvSeriesId != null) {
//            val destination = SeasonDetailsScreenDestination(
//                tvSeriesId = tvSeriesId,
//                seasonNumber = seasonNumber,
//                startRoute = uiState.startRoute
//            )
//
//            navigator.navigate(destination)
//        }
//    }
//    val onSimilarMoreClicked = {
//        val tvSeriesId = uiState.tvSeriesDetails?.id
//
//        if (tvSeriesId != null) {
//            val destination = RelatedTvSeriesScreenDestination(
//                tvSeriesId = tvSeriesId,
//                type = RelationType.Similar,
//                startRoute = uiState.startRoute
//            )
//
//            navigator.navigate(destination)
//        }
//    }

//    val onRecommendationsMoreClicked = {
//        val tvSeriesId = uiState.tvSeriesDetails?.id
//
//        if (tvSeriesId != null) {
//            val destination = RelatedTvShowScreenDestination(
//                tvSeriesId = tvSeriesId,
//                type = RelationType.Recommended,
//                startRoute = uiState.startRoute
//            )
//
//            navigator.navigate(destination)
//        }
//    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun TvShowDetailsScreenContent(
    uiState: TvShowDetailsScreenUIState,
    onBackClicked: () -> Unit,
    onExternalIdClicked: (id: ExternalId) -> Unit,
    onShareClicked: (details: ShareDetails) -> Unit,
    onVideoClicked: (video: Video) -> Unit,
    onFavoriteClicked: (details: TvShowDetails) -> Unit,
    onCloseClicked: () -> Unit,
    onCreatorClicked: (personId: Int) -> Unit,
    onTvShowClicked: (tvShowId: Int) -> Unit,
    onSeasonClicked: (seasonNumber: Int) -> Unit,
    onSimilarMoreClicked: () -> Unit,
    onRecommendationsMoreClicked: () -> Unit,
    onReviewsClicked: () -> Unit
) {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    val similar = uiState.associatedTvShow.similar.collectAsLazyPagingItems()
    val recommendations = uiState.associatedTvShow.recommendations.collectAsLazyPagingItems()

    val scrollState = rememberScrollState()
    val scrollToStart = {
        coroutineScope.launch {
            scrollState.animateScrollTo(0)
        }
    }
    val imdbExternalId by derivedStateOf {
        uiState.associatedContent.externalIds?.filterIsInstance<ExternalId.Imdb>()?.firstOrNull()
    }

    var showErrorDialog by remember { mutableStateOf(false) }

    var topSectionHeight: Float? by remember { mutableStateOf(null) }
    val appbarHeight = density.run { 56.dp.toPx() }
    val topSectionScrollLimitValue: Float? = topSectionHeight?.minus(appbarHeight)

    LaunchedEffect(uiState.error) {
        showErrorDialog = uiState.error != null
    }

    BackHandler(showErrorDialog) {
        showErrorDialog = false
    }

    if (showErrorDialog) {
        MovplayErrorDialog(
            onDismissRequest = {
                showErrorDialog = false
            },
            onConfirmClick = {
                showErrorDialog = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            MovplayPresentableDetailsTopSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        topSectionHeight = coordinates.size.height.toFloat()
                    },
                presentable = uiState.tvShowDetails,
                backdrops = uiState.associatedContent.backdrops,
                scrollState = scrollState,
                scrollValueLimit = topSectionScrollLimitValue
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(MaterialTheme.spacing.small)
                ) {
                    MovplayTvShowDetailsTopContent(
                        modifier = Modifier.fillMaxWidth(),
                        tvShowDetails = uiState.tvShowDetails
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Crossfade(
                    modifier = Modifier.fillMaxWidth(),
                    targetState = uiState.associatedContent.externalIds
                ) { ids ->
                    if (ids != null) {
                        MovplayExternalIdsSection(
                            modifier = Modifier.fillMaxWidth(),
                            externalIds = ids,
                            onExternalIdClick = onExternalIdClicked
                        )
                    }
                }
            }

        }
    }
}