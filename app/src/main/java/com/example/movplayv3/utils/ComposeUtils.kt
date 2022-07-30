package com.example.movplayv3.utils

import android.annotation.SuppressLint
import androidx.annotation.PluralsRes
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.defaultPlaceholder() = composed {
    placeholder(
        visible = true,
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            animationSpec = InfiniteRepeatableSpec(
                animation = tween(durationMillis = 500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        ),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium
    )
}


@Composable
fun LazyListState.isScrollingTowardsStart(): Boolean {
    var previousIndex by remember(this) {
        mutableStateOf(firstVisibleItemIndex)
    }
    var previousScrollOffset by remember(this) {
        mutableStateOf(firstVisibleItemScrollOffset)
    }

    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun LazyGridState.isScrollingTowardsStart(): Boolean {
    var previousIndex by remember(this) {
        mutableStateOf(firstVisibleItemIndex)
    }
    var previousScrollOffset by remember(this) {
        mutableStateOf(firstVisibleItemScrollOffset)
    }

    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun pluralsResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any? = emptyArray()
): String {
    return LocalContext.current.resources
        .getQuantityString(resId, quantity, *formatArgs)
}

@Composable
fun BoxWithConstraintsScope.getMaxSizeInt(): Pair<Int, Int> {
    return LocalDensity.current.run { maxWidth.toPx().toInt() to maxHeight.toPx().toInt() }
}

@ExperimentalFoundationApi
fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyGridItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}

fun LazyPagingItems<*>.hasItems() = itemCount > 0

fun LazyPagingItems<*>.hasNoItems() = !hasItems()

fun LazyPagingItems<*>.isEmpty(): Boolean {
    return run {
        loadState.source.refresh is LoadState.NotLoading
                && loadState.append.endOfPaginationReached
                && itemCount < 1
    }
}

fun LazyPagingItems<*>.isNotEmpty(): Boolean = !isEmpty()

inline fun <T> List<T>?.ifNotNullAndEmpty(scope: (List<T>) -> Unit) {
    if (!isNullOrEmpty()) {
        scope(this)
    }
}

@Composable
fun partiallyAnnotatedString(
    text: String,
    content: String,
    textStyle: SpanStyle = SpanStyle(Color.White),
    contentStyle: SpanStyle = SpanStyle(MaterialTheme.colorScheme.primary)
): AnnotatedString = buildAnnotatedString {
    if (text.contains(content)) {
        val (beforeText, afterText) = text.split(content).run {
            firstOrNull() to lastOrNull()
        }

        if (!beforeText.isNullOrBlank()) {
            withStyle(textStyle) {
                append(beforeText)
            }
        }

        withStyle(contentStyle) {
            append(content)
        }

        if (!afterText.isNullOrBlank()) {
            withStyle(textStyle) {
                append(afterText)
            }
        }
    } else {
        withStyle(textStyle) {
            append(text)
        }
    }
}

fun Iterable<LazyPagingItems<*>>.isAnyRefreshing(): Boolean {
    return any { it.itemCount > 0 && it.loadState.refresh is LoadState.Loading }
}

fun Iterable<LazyPagingItems<*>>.refreshAll() {
    return forEach { it.refresh() }
}