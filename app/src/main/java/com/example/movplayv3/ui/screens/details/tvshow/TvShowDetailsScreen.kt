package com.example.movplayv3.ui.screens.details.tvshow

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.model.ShareDetails
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.ui.screens.destinations.TvShowDetailsScreenDestination
import com.example.movplayv3.utils.openExternalId
import com.example.movplayv3.utils.openVideo
import com.example.movplayv3.utils.shareImdb
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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