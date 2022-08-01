package com.example.movplayv3.ui.components.sections

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.movplayv3.R
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.PresentableItemState
import com.example.movplayv3.ui.components.button.MovplayScrollToStartButton
import com.example.movplayv3.ui.components.items.MovplayPresentableItem
import com.example.movplayv3.ui.components.texts.MovplaySectionLabel
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.isScrollingTowardsStart
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun MovplayPresentableSection(
    title: String,
    state: LazyPagingItems<out Presentable>,
    modifier: Modifier = Modifier,
    scrollToBeginningItemsStart: Int = 30,
    showLoadingAtRefresh: Boolean = true,
    showMoreButton: Boolean = true,
    onMoreClick: () -> Unit = {},
    onPresentableClick: (Int) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val isScrollingLeft = listState.isScrollingTowardsStart()

    val showScrollToBeginningButton by derivedStateOf {
        val visibleMaxItem = listState.firstVisibleItemIndex > scrollToBeginningItemsStart
        visibleMaxItem && isScrollingLeft
    }
    val onScrollToStartClick: () -> Unit = {
        coroutineScope.launch {
            listState.animateScrollToItem(0)
        }
    }
    val isNotEmpty by derivedStateOf {
        state.run {
            if (showLoadingAtRefresh) {
                loadState.refresh is LoadState.Loading || itemCount > 0
            } else {
                itemCount > 0
            }
        }
    }
    if (isNotEmpty) {
        Column(modifier = modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovplaySectionLabel(
                    modifier = Modifier.weight(1f),
                    text = title
                )
                if (showMoreButton) {
                    TextButton(onClick = onMoreClick) {
                        Text(
                            text = stringResource(R.string.movies_more),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                }
            }
            Box {
                LazyRow(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.small),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                    contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium)
                ) {
                    items(state) { movie ->
                        movie?.let {
                            MovplayPresentableItem(
                                //modifier = Modifier.animateItemPlacement(),
                                presentableState = PresentableItemState.Result(movie),
                                onClick = { onPresentableClick(it.id) }
                            )
                        }
                    }
                    state.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                items(10) {
                                    MovplayPresentableItem(presentableState = PresentableItemState.Loading)
                                }
                            }
                            loadState.append is LoadState.Loading -> {
                                item { MovplayPresentableItem(presentableState = PresentableItemState.Loading) }
                            }
//                            loadState.refresh is LoadState.Error -> {
//                                val e = state.loadState.refresh as LoadState.Error
//
//                                item { MovieItem(presentableState = PresentableState.Error) }
//                            }
//                            loadState.append is LoadState.Error -> {
//                                val e = state.loadState.refresh as LoadState.Error
//
//                                item { MovieItem(presentableState = PresentableState.Error) }
//                            }
                        }
                    }
                }
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = MaterialTheme.spacing.small),
                    visible = showScrollToBeginningButton,
                    enter = slideIn(
                        animationSpec = tween(),
                        initialOffset = { size -> IntOffset(-size.width, 0) }),
                    exit = fadeOut(animationSpec = spring()) + scaleOut(
                        animationSpec = spring(),
                        targetScale = 0.3f
                    ),
                ) {
                    MovplayScrollToStartButton(
                        onClick = onScrollToStartClick
                    )
                }
            }
        }
    }
}