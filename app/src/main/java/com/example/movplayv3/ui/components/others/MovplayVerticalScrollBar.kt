package com.example.movplayv3.ui.components.others

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.gridVerticalScrollBar(
    state: LazyGridState,
    width: Dp = 4.dp
): Modifier = composed {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val scrollbarColor = MaterialTheme.colorScheme.primary
    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration)
    )

    var currentScrollBarHeight by remember {
        mutableStateOf(0f)
    }
    var currentScrollbarOffsetY by remember {
        mutableStateOf(0f)
    }

    val scrollBarHeight by animateFloatAsState(currentScrollBarHeight)
    val scrollbarOffsetY by animateFloatAsState(currentScrollbarOffsetY)
    drawWithContent {
        drawContent()
        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollBar = state.isScrollInProgress || alpha > 0.0f

        if (needDrawScrollBar && firstVisibleElementIndex != null) {
            val elementHeight = size.height / state.layoutInfo.totalItemsCount

            currentScrollbarOffsetY = firstVisibleElementIndex * elementHeight
            currentScrollBarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

            drawRoundRect(
                color = scrollbarColor,
                topLeft = Offset(size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollBarHeight),
                cornerRadius = CornerRadius(x = width.toPx() / 2, y = width.toPx() / 2),
                alpha = alpha
            )
        }
    }
}

fun Modifier.listVerticalScrollBar(
    state: LazyListState,
    width: Dp = 4.dp
): Modifier = composed {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val scrollbarColor = MaterialTheme.colorScheme.primary
    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration)
    )

    var currentScrollBarHeight by remember {
        mutableStateOf(0f)
    }
    var currentScrollbarOffsetY by remember {
        mutableStateOf(0f)
    }

    val scrollBarHeight by animateFloatAsState(currentScrollBarHeight)
    val scrollbarOffsetY by animateFloatAsState(currentScrollbarOffsetY)

    drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

        if (needDrawScrollbar && firstVisibleElementIndex != null) {
            val elementHeight = size.height / state.layoutInfo.totalItemsCount

            currentScrollbarOffsetY = firstVisibleElementIndex * elementHeight
            currentScrollBarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

            drawRoundRect(
                color = scrollbarColor,
                topLeft = Offset(size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollBarHeight),
                cornerRadius = CornerRadius(x = width.toPx() / 2, y = width.toPx() / 2),
                alpha = alpha
            )
        }
    }
}