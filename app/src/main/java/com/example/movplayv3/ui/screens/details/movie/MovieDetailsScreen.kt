package com.example.movplayv3.ui.screens.details.movie

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
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
import com.example.movplayv3.R
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.model.ShareDetails
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.ui.components.button.MovplayBackButton
import com.example.movplayv3.ui.components.button.MovplayLikeButton
import com.example.movplayv3.ui.components.dialogs.MovplayErrorDialog
import com.example.movplayv3.ui.components.others.MovplayAnimatedContentContainer
import com.example.movplayv3.ui.components.others.MovplayDetailsAppBar
import com.example.movplayv3.ui.components.sections.*
import com.example.movplayv3.ui.screens.destinations.MovieDetailsScreenDestination
import com.example.movplayv3.ui.screens.destinations.PersonDetailsScreenDestination
import com.example.movplayv3.ui.screens.details.components.MovplayMovieDetailsInfoSection
import com.example.movplayv3.ui.screens.details.components.MovplayMovieDetailsTopContent
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination(
    navArgsDelegate = MovieDetailsScreenArgs::class,
    style = MovieDetailsScreenTransitions::class
)
@Composable
fun AnimatedVisibilityScope.MovieDetailsScreen(
    viewModel: MovieDetailsScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }
    val onFavouriteClicked: (details: MovieDetails) -> Unit = { details ->
        if (uiState.additionalMovieDetailsInfo.isFavorite) {
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
    val onMemberClicked = { personId: Int ->
        val destination = PersonDetailsScreenDestination(
            personId = personId,
            startRoute = uiState.startRoute
        )

        navigator.navigate(destination)
    }
    val onMovieClicked = { movieId: Int ->
        val destination = MovieDetailsScreenDestination(
            movieId = movieId,
            startRoute = uiState.startRoute
        )

        navigator.navigate(destination)
    }

//    val onReviewsClicked: () -> Unit = {
//        val movieId = uiState.movieDetails?.id
//
//        if (movieId != null) {
//            val destination = ReviewsScreenDestination(
//                startRoute = uiState.startRoute,
//                mediaId = movieId,
//                type = MediaType.Movie
//            )
//
//            navigator.navigate(destination)
//        }
//    }
//
//
//    val onSimilarMoreClicked = {
//        val movieId = uiState.movieDetails?.id
//
//        if (movieId != null) {
//            val destination = RelatedMoviesScreenDestination(
//                movieId = movieId,
//                type = RelationType.Similar,
//                startRoute = uiState.startRoute
//            )
//
//            navigator.navigate(destination)
//        }
//    }
//
//    val onRecommendationsMoreClicked = {
//        val movieId = uiState.movieDetails?.id
//
//        if (movieId != null) {
//            val destination = RelatedMoviesScreenDestination(
//                movieId = movieId,
//                type = RelationType.Recommended,
//                startRoute = uiState.startRoute
//            )
//
//            navigator.navigate(destination)
//        }
//    }
    MovieDetailsScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onExternalIdClicked = onExternalIdClicked,
        onShareClicked = onShareClicked,
        onVideoClicked = onVideoClicked,
        onFavouriteClicked = onFavouriteClicked,
        onCloseClicked = onCloseClicked,
        onMemberClicked = onMemberClicked,
        onMovieClicked = onMovieClicked,
        onSimilarMoreClicked = {},
        onRecommendationsMoreClicked = {},
        onReviewsClicked = {}
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MovieDetailsScreenContent(
    uiState: MovieDetailsScreenUIState,
    onBackClicked: () -> Unit,
    onExternalIdClicked: (id: ExternalId) -> Unit,
    onShareClicked: (details: ShareDetails) -> Unit,
    onVideoClicked: (video: Video) -> Unit,
    onFavouriteClicked: (details: MovieDetails) -> Unit,
    onCloseClicked: () -> Unit,
    onMemberClicked: (personId: Int) -> Unit,
    onMovieClicked: (movieId: Int) -> Unit,
    onSimilarMoreClicked: () -> Unit,
    onRecommendationsMoreClicked: () -> Unit,
    onReviewsClicked: () -> Unit
) {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    val similarMoviesState = uiState.associatedMovies.similar.collectAsLazyPagingItems()
    val moviesRecommendationState =
        uiState.associatedMovies.recommendations.collectAsLazyPagingItems()
    val otherDirectorMoviesState =
        uiState.associatedMovies.directorMovies.movies.collectAsLazyPagingItems()

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
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
                presentable = uiState.movieDetails,
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
                    MovplayMovieDetailsTopContent(
                        modifier = Modifier.fillMaxWidth(),
                        movieDetails = uiState.movieDetails
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
            MovplayMovieDetailsInfoSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .animateContentSize(),
                movieDetails = uiState.movieDetails,
                watchAtTime = uiState.additionalMovieDetailsInfo.watchAtTime,
                imdbExternalId = imdbExternalId,
                onShareClicked = onShareClicked
            )

            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = uiState.additionalMovieDetailsInfo.watchProviders != null
            ) {
                if (uiState.additionalMovieDetailsInfo.watchProviders != null) {
                    MovplayWatchProvidersSection(
                        modifier = Modifier.fillMaxWidth(),
                        watchProviders = uiState.additionalMovieDetailsInfo.watchProviders,
                        title = stringResource(R.string.available_at)
                    )
                }
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = !uiState.additionalMovieDetailsInfo.credits?.cast.isNullOrEmpty()
            ) {
                uiState.additionalMovieDetailsInfo.credits?.cast?.ifNotNullAndEmpty { members ->
                    MovplayMemberSection(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.movie_details_cast),
                        members = members,
                        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
                        onMemberClick = onMemberClicked
                    )
                }
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = !uiState.additionalMovieDetailsInfo.credits?.crew.isNullOrEmpty()
            ) {
                uiState.additionalMovieDetailsInfo.credits?.crew.ifNotNullAndEmpty { members ->
                    MovplayMemberSection(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.movie_details_crew),
                        members = members,
                        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
                        onMemberClick = onMemberClicked
                    )
                }
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = uiState.associatedMovies.collection?.run { parts.isNotEmpty() } == true
            ) {
                val movieCollection = uiState.associatedMovies.collection

                if (movieCollection != null && movieCollection.parts.isNotEmpty()) {
                    MovplayPresentableListSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(vertical = MaterialTheme.spacing.small),
                        title = movieCollection.name,
                        list = movieCollection.parts.sortedBy { part -> part.releaseDate },
                        selectedId = uiState.movieDetails?.id,
                        onPresentableClick = { movieId ->
                            if (movieId != uiState.movieDetails?.id) {
                                onMovieClicked(movieId)
                            } else {
                                scrollToStart()
                            }
                        }
                    )
                }
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = otherDirectorMoviesState.isNotEmpty()
            ) {
                MovplayPresentableSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(
                        id = R.string.movie_details_director_movies,
                        uiState.associatedMovies.directorMovies.directorName
                    ),
                    state = otherDirectorMoviesState,
                    showLoadingAtRefresh = false,
                    showMoreButton = false,
                    onMoreClick = onSimilarMoreClicked,
                    onPresentableClick = { movieId ->
                        if (movieId != uiState.movieDetails?.id) {
                            onMovieClicked(movieId)
                        } else {
                            scrollToStart()
                        }
                    }
                )
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = moviesRecommendationState.isNotEmpty()
            ) {
                MovplayPresentableSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.movie_details_recommendations),
                    state = moviesRecommendationState,
                    showLoadingAtRefresh = false,
                    onMoreClick = onRecommendationsMoreClicked,
                    onPresentableClick = { movieId ->
                        if (movieId != uiState.movieDetails?.id) {
                            onMovieClicked(movieId)
                        } else {
                            scrollToStart()
                        }
                    }
                )
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = similarMoviesState.isNotEmpty()
            ) {
                val movieCollection = uiState.associatedMovies.collection

                MovplayPresentableSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = if (movieCollection != null) {
                        "Similar with ${movieCollection.name}"
//                        stringResource(
//                            id = R.string.movie_details_similar_with,
//                            movieCollection.name
//                        )
                    } else {
                        stringResource(id = R.string.movie_details_similar)
                    },
                    state = similarMoviesState,
                    showLoadingAtRefresh = false,
                    onMoreClick = onSimilarMoreClicked,
                    onPresentableClick = { movieId ->
                        if (movieId != uiState.movieDetails?.id) {
                            onMovieClicked(movieId)
                        } else {
                            scrollToStart()
                        }
                    }
                )
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = !uiState.associatedContent.videos.isNullOrEmpty()
            ) {
                MovplayVideosSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.season_details_videos_label),
                    videos = uiState.associatedContent.videos ?: emptyList(),
                    contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
                    onVideoClicked = onVideoClicked
                )
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = uiState.additionalMovieDetailsInfo.reviewsCount > 0
            ) {
                MovplayReviewSection(
                    modifier = Modifier.fillMaxWidth(),
                    count = uiState.additionalMovieDetailsInfo.reviewsCount,
                    onClick = onReviewsClicked
                )
            }
            Spacer(
                modifier = Modifier.windowInsetsBottomHeight(
                    insets = WindowInsets(bottom = MaterialTheme.spacing.medium)
                )
            )
        }
        MovplayDetailsAppBar(
            modifier = Modifier.align(Alignment.TopCenter),
            title = null,
            backgroundColor = MaterialTheme.colorScheme.surface.copy(alpha = 0f),
            scrollState = scrollState,
            transparentScrollValueLimit = topSectionScrollLimitValue,
            action = {
                MovplayBackButton(
                    onBackClicked
                )
            },
            trailing = {
                MovplayLikeButton(
                    isFavourite = uiState.additionalMovieDetailsInfo.isFavorite,
                    onClick = {
                        val details = uiState.movieDetails

                        if (details != null) {
                            onFavouriteClicked(details)
                        }
                    }
                )
            }
        )
    }
}