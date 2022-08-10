package com.example.movplayv3.ui.screens.discover.movies

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movplayv3.R
import com.example.movplayv3.data.model.SortOrder
import com.example.movplayv3.data.model.SortType
import com.example.movplayv3.ui.components.button.MovplayFilterFloatingButton
import com.example.movplayv3.ui.components.button.MovplaySortTypeDropdownButton
import com.example.movplayv3.ui.components.others.MovplayBasicAppBar
import com.example.movplayv3.ui.components.others.MovplayFilterEmptyState
import com.example.movplayv3.ui.components.sections.MovplayPresentableGridSection
import com.example.movplayv3.ui.screens.destinations.MovieDetailsScreenDestination
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import com.example.movplayv3.ui.screens.discover.components.FilterMoviesModalBottomSheetContent
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.isEmpty
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination(style = DiscoverMoviesScreenTransitions::class)
@Composable
fun DiscoverMoviesScreen(
    viewModel: DiscoverMoviesViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val onBackClicked: () -> Unit = {navigator.navigateUp()}
    val onSortOrderChanged: (order: SortOrder) -> Unit = viewModel::onSortOrderChange
    val onSortTypeChanged: (type: SortType) -> Unit = viewModel::onSortTypeChange
    val onMovieClicked: (movieId: Int) -> Unit = { id ->
        val destination = MovieDetailsScreenDestination(
            movieId = id,
            startRoute = MovieScreenDestination.route
        )

        navigator.navigate(destination)
    }
    val onSaveFilterClicked: (state: MovieFilterState) -> Unit = viewModel::onFilterStateChange

    DiscoverMoviesScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onSortOrderChanged = onSortOrderChanged,
        onSortTypeChanged = onSortTypeChanged,
        onMovieClicked = onMovieClicked,
        onSaveFilterClicked = onSaveFilterClicked
    )
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun DiscoverMoviesScreenContent(
    uiState: DiscoverMoviesScreenUIState,
    onBackClicked: () -> Unit,
    onSortOrderChanged: (order: SortOrder) -> Unit,
    onSortTypeChanged: (type: SortType) -> Unit,
    onMovieClicked: (movieId: Int) -> Unit,
    onSaveFilterClicked: (state: MovieFilterState) -> Unit
){
    val movies = uiState.movies.collectAsLazyPagingItems()

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val gridState = rememberLazyGridState()

    val showFloatingButton = if (gridState.isScrollInProgress) {
        false
    } else {
        !sheetState.isVisible
    }

    val orderIconRotation by animateFloatAsState(
        targetValue = if (uiState.sortInfo.sortOrder == SortOrder.Desc) {
            0f
        } else 180f
    )

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            FilterMoviesModalBottomSheetContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .navigationBarsPadding(),
                sheetState = sheetState,
                filterState = uiState.filterState,
                onCloseClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                onSaveFilterClick = { filterState ->
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                    onSaveFilterClicked(filterState)
                }
            )
        },
        sheetBackgroundColor = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                MovplayBasicAppBar(
                    title = stringResource(R.string.discover_movies_appbar_title),
                    action = {
                        IconButton(onClick = onBackClicked) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "go back",
                            )
                        }
                    },
                    trailing = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                modifier = Modifier.rotate(orderIconRotation),
                                onClick = {
                                    val order =
                                        if (uiState.sortInfo.sortOrder == SortOrder.Desc) {
                                            SortOrder.Asc
                                        } else {
                                            SortOrder.Desc
                                        }

                                    onSortOrderChanged(order)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowDownward,
                                    contentDescription = "sort order",
                                )
                            }

                            MovplaySortTypeDropdownButton(
                                selectedType = uiState.sortInfo.sortType,
                                onTypeSelected = onSortTypeChanged
                            )
                        }
                    })

                Crossfade(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    targetState = !movies.isEmpty()
                ) { hasFilterResults ->
                    if (hasFilterResults) {
                        MovplayPresentableGridSection(
                            modifier = Modifier.fillMaxSize(),
                            gridState = gridState,
                            contentPadding = PaddingValues(
                                top = MaterialTheme.spacing.medium,
                                start = MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.small,
                                bottom = MaterialTheme.spacing.large
                            ),
                            state = movies,
                            onPresentableClick = onMovieClicked
                        )
                    } else {
                        MovplayFilterEmptyState(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = MaterialTheme.spacing.medium)
                                .padding(top = MaterialTheme.spacing.extraLarge),
                            onFilterButtonClicked = {
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            }
                        )
                    }
                }
            }

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomEnd),
                visible = showFloatingButton,
                enter = fadeIn(animationSpec = spring()) + scaleIn(
                    animationSpec = spring(),
                    initialScale = 0.3f
                ),
                exit = fadeOut(animationSpec = spring()) + scaleOut(
                    animationSpec = spring(),
                    targetScale = 0.3f
                )
            ) {
                MovplayFilterFloatingButton(
                    modifier = androidx.compose.ui.Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .navigationBarsPadding()
                        .imePadding(),
                    onClick = {
                        coroutineScope.launch {
                            if (sheetState.isVisible) {
                                sheetState.hide()
                            } else {
                                sheetState.show()
                            }
                        }
                    }
                )
            }
        }
    }
}