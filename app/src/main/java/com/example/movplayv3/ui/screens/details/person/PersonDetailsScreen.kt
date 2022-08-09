package com.example.movplayv3.ui.screens.details.person

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movplayv3.R
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.model.MediaType
import com.example.movplayv3.ui.components.dialogs.MovplayErrorDialog
import com.example.movplayv3.ui.components.others.MovplayAnimatedContentContainer
import com.example.movplayv3.ui.components.others.MovplayBasicAppBar
import com.example.movplayv3.ui.components.sections.MovplayExternalIdsSection
import com.example.movplayv3.ui.screens.destinations.MovieDetailsScreenDestination
import com.example.movplayv3.ui.screens.destinations.TvShowDetailsScreenDestination
import com.example.movplayv3.ui.screens.details.components.MovplayCreditsList
import com.example.movplayv3.ui.screens.details.components.MovplayPersonDetailsInfoSection
import com.example.movplayv3.ui.screens.details.components.MovplayPersonDetailsTopContent
import com.example.movplayv3.ui.screens.details.components.MovplayPersonProfileImage
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.openExternalId
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Destination(
    navArgsDelegate = PersonDetailsScreenArgs::class,
    style = PersonDetailsScreenTransitions::class
)
@Composable
fun AnimatedVisibilityScope.PersonDetailsScreen(
    viewModel: PersonDetailsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }
    val onCloseClicked: () -> Unit = {
        navigator.popBackStack(uiState.startRoute, inclusive = false)
    }
    val onExternalIdClicked = { id: ExternalId ->
        openExternalId(
            context = context,
            externalId = id
        )
    }
    val onMediaClicked = { mediaType: MediaType, id: Int ->
        val destination = when (mediaType) {
            MediaType.Movie -> {
                MovieDetailsScreenDestination(
                    movieId = id,
                    startRoute = uiState.startRoute
                )
            }

            MediaType.Tv -> {
                TvShowDetailsScreenDestination(
                    tvShowId = id,
                    startRoute = uiState.startRoute
                )
            }

            else -> null
        }

        if (destination != null) {
            navigator.navigate(destination)
        }
    }
    PersonDetailsScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onCloseClicked = onCloseClicked,
        onExternalIdClicked = onExternalIdClicked,
        onMediaClicked = onMediaClicked
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun PersonDetailsScreenContent(
    uiState: PersonDetailsScreenUIState,
    onBackClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    onExternalIdClicked: (id: ExternalId) -> Unit,
    onMediaClicked: (type: MediaType, id: Int) -> Unit
) {
    val personDetailsState by derivedStateOf {
        uiState.details?.let { PersonDetailsState.Result(it) }
            ?: PersonDetailsState.Loading
    }
    var showErrorDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(uiState.error) {
        showErrorDialog = uiState.error != null
        Timber.tag("TAG").e("PersonDetailsScreenContent: ")
    }
    BackHandler(showErrorDialog) {
        showErrorDialog = false
    }

    if (showErrorDialog) {
        MovplayErrorDialog(onDismissRequest = {
            showErrorDialog = false
        }, onConfirmClick = {
            showErrorDialog = false
        })
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 56.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium)
            ) {
                val (profileImageRef, contentRef) = createRefs()
                MovplayPersonProfileImage(
                    modifier = Modifier.constrainAs(profileImageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                    personDetailsState = personDetailsState
                )
                Column(
                    modifier = Modifier
                        .constrainAs(contentRef) {
                            start.linkTo(profileImageRef.end)
                            end.linkTo(parent.end)
                            top.linkTo(profileImageRef.top)
                            bottom.linkTo(profileImageRef.bottom)

                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = MaterialTheme.spacing.medium)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(MaterialTheme.spacing.small)
                    ) {
                        MovplayPersonDetailsTopContent(
                            modifier = Modifier.fillMaxWidth(),
                            personDetails = uiState.details
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    uiState.externalIds?.let { ids ->
                        MovplayExternalIdsSection(
                            modifier = Modifier.fillMaxWidth(),
                            externalIds = ids,
                            onExternalIdClick = onExternalIdClicked
                        )
                    }
                }
            }
            MovplayPersonDetailsInfoSection(
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize(),
                personDetails = uiState.details
            )
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = !uiState.credits?.cast.isNullOrEmpty()
            ) {
                MovplayCreditsList(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.person_details_screen_cast),
                    credits = uiState.credits?.cast ?: emptyList(),
                    onCreditsClick = onMediaClicked
                )
            }
            MovplayAnimatedContentContainer(
                modifier = Modifier.fillMaxWidth(),
                visible = !uiState.credits?.crew.isNullOrEmpty()
            ) {
                MovplayCreditsList(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.person_details_screen_crew),
                    credits = uiState.credits?.crew ?: emptyList(),
                    onCreditsClick = onMediaClicked
                )
            }

            Spacer(
                modifier = Modifier.windowInsetsBottomHeight(
                    insets = WindowInsets(bottom = MaterialTheme.spacing.medium)
                )
            )
        }
        MovplayBasicAppBar(
            modifier = Modifier.align(Alignment.TopCenter),
            title = stringResource(R.string.person_details_screen_appbar_label),
//            backgroundColor = Color.Black.copy(0.7f),
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