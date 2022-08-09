package com.example.movplayv3.ui.screens.details.person

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.model.MediaType
import com.example.movplayv3.ui.components.dialogs.MovplayErrorDialog
import com.example.movplayv3.ui.screens.destinations.MovieDetailsScreenDestination
import com.example.movplayv3.ui.screens.destinations.TvShowDetailsScreenDestination
import com.example.movplayv3.ui.screens.details.components.MovplayPersonProfileImage
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.openExternalId
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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
            }
        }
    }
}