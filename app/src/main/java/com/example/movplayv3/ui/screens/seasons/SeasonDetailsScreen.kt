package com.example.movplayv3.ui.screens.seasons

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movplayv3.R
import com.example.movplayv3.ui.components.chips.MovplayEpisodeChip

import com.example.movplayv3.ui.components.dialogs.MovplayErrorDialog
import com.example.movplayv3.ui.components.others.MovplayAnimatedContentContainer
import com.example.movplayv3.ui.components.others.MovplayBasicAppBar
import com.example.movplayv3.ui.components.sections.MovplayMemberSection
import com.example.movplayv3.ui.components.sections.MovplayPresentableDetailsTopSection
import com.example.movplayv3.ui.components.sections.MovplayVideosSection
import com.example.movplayv3.ui.components.texts.MovplayExpandableText
import com.example.movplayv3.ui.components.texts.MovplayLabeledText
import com.example.movplayv3.ui.components.texts.MovplaySectionLabel
import com.example.movplayv3.ui.screens.destinations.PersonDetailsScreenDestination
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.formatted
import com.example.movplayv3.utils.openVideo
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    navArgsDelegate = SeasonDetailsScreenArgs::class,
    style = SeasonDetailsScreenTransitions::class
)
@Composable
fun AnimatedVisibilityScope.SeasonDetailsScreen(
    viewModel: SeasonDetailsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }
    val onCloseClicked: () -> Unit = {
        navigator.popBackStack(uiState.startRoute, inclusive = false)
    }
    val onMemberClicked = { personId: Int ->
        val destination = PersonDetailsScreenDestination(
            personId = personId,
            startRoute = uiState.startRoute
        )

        navigator.navigate(destination)
    }
    val onEpisodeExpanded: (episodeNumber: Int) -> Unit = viewModel::getEpisodeStills

    SeasonDetailsContent(
        uiState = uiState,
        onCloseClicked = onCloseClicked,
        onBackClicked = onBackClicked,
        onMemberClicked = onMemberClicked,
        onEpisodeExpanded = onEpisodeExpanded
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun SeasonDetailsContent(
    uiState: SeasonDetailsScreenUiState,
    onCloseClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onMemberClicked: (Int) -> Unit,
    onEpisodeExpanded: (episodeNumber: Int) -> Unit
) {
    val context = LocalContext.current

    val lazyState = rememberLazyListState()

    var showErrorDialog by remember { mutableStateOf(false) }

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
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            state = lazyState,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                MovplayPresentableDetailsTopSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    presentable = uiState.seasonDetails
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
                    ) {
                        uiState.seasonDetails?.seasonNumber?.let { number ->
                            MovplayLabeledText(
                                label = stringResource(R.string.season_details_season_number_label),
                                text = number.toString()
                            )
                        }
                        uiState.episodeCount?.let { count ->
                            MovplayLabeledText(
                                label = stringResource(R.string.season_details_episodes_count_label),
                                text = count.toString()
                            )
                        }
                        uiState.seasonDetails?.airDate?.let { date ->
                            MovplayLabeledText(
                                label = stringResource(R.string.season_details_air_date_label),
                                text = date.formatted()
                            )
                        }
                    }
                }
            }

            item {
                MovplayAnimatedContentContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.medium),
                    visible = uiState.seasonDetails != null
                ) {
                    if (uiState.seasonDetails != null) {
                        Column(
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
                        ) {
                            Text(
                                text = uiState.seasonDetails.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            if (uiState.seasonDetails.overview.isNotBlank()) {
                                MovplayExpandableText(
                                    modifier = Modifier.fillMaxSize(),
                                    text = uiState.seasonDetails.overview
                                )
                            }
                        }
                    }
                }
            }

            item {
                MovplayAnimatedContentContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.medium),
                    visible = !uiState.aggregatedCredits?.cast.isNullOrEmpty()
                ) {
                    MovplayMemberSection(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.season_details_cast_label),
                        members = uiState.aggregatedCredits?.cast ?: emptyList(),
                        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
                        onMemberClick = onMemberClicked
                    )
                }
            }

            item {
                MovplayAnimatedContentContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.medium),
                    visible = !uiState.aggregatedCredits?.crew.isNullOrEmpty()
                ) {
                    MovplayMemberSection(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.season_details_crew_label),
                        members = uiState.aggregatedCredits?.crew ?: emptyList(),
                        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
                        onMemberClick = onMemberClicked
                    )
                }
            }

            item {
                MovplayAnimatedContentContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.medium),
                    visible = !uiState.videos.isNullOrEmpty()
                ) {
                    MovplayVideosSection(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.tv_series_details_videos),
                        videos = uiState.videos ?: emptyList(),
                        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium)
                    ) { video ->
                        openVideo(
                            context = context,
                            video = video
                        )
                    }
                }
            }

            uiState.seasonDetails?.episodes?.let { episodes ->
                if (episodes.isNotEmpty()) {
                    item {
                        MovplaySectionLabel(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MaterialTheme.spacing.medium)
                                .padding(
                                    top = MaterialTheme.spacing.medium,
                                    bottom = MaterialTheme.spacing.small
                                ),
                            text = stringResource(R.string.season_details_episodes_label)
                        )
                    }
                }

                itemsIndexed(episodes) { index, episode ->
                    val bottomPadding = if (index < episodes.count() - 1) {
                        MaterialTheme.spacing.medium
                    } else {
                        MaterialTheme.spacing.default
                    }

                    var expanded by rememberSaveable(episode.id.toString()) {
                        mutableStateOf(false)
                    }

                    val stills by derivedStateOf {
                        uiState.episodeStills.getOrElse(
                            episode.episodeNumber,
                            defaultValue = { null })
                    }

                    MovplayEpisodeChip(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.medium)
                            .padding(bottom = bottomPadding),
                        episode = episode,
                        stills = stills,
                        expanded = expanded,
                        enabled = episode.isReleased()
                    ) {
                        expanded = !expanded
                        if (expanded) {
                            onEpisodeExpanded(episode.episodeNumber)
                        }
                    }
                }
            }

            item {
                Spacer(
                    modifier = Modifier.windowInsetsBottomHeight(
                        insets = WindowInsets(bottom = MaterialTheme.spacing.large)
                    )
                )
            }
        }

        MovplayBasicAppBar(
            modifier = Modifier.align(Alignment.TopCenter),
            title = stringResource(R.string.season_details_appbar_label),
            action = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "go back",
                    )
                }
            },
        )
    }
}