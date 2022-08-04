package com.example.movplayv3.ui.components.others

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovplayAnimatedContentContainer(
    modifier: Modifier = Modifier,
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit = {}
) {
    AnimatedVisibility(
        modifier = modifier,
        enter = fadeIn(tween(300, delayMillis = 100)) + expandVertically(tween(300)),
        exit = fadeOut(tween(300)) + shrinkVertically(tween(300, delayMillis = 100)),
        visible = visible,
        content = content
    )
}