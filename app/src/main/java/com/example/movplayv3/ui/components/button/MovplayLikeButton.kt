package com.example.movplayv3.ui.components.button

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovplayLikeButton(
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        AnimatedContent(
            targetState = isFavourite,
            contentAlignment = Alignment.Center,
            transitionSpec = {
                fadeIn(animationSpec = tween(200)) + scaleIn(
                    animationSpec = tween(200, delayMillis = 200),
                    initialScale = 0.8f
                ) with scaleOut(
                    animationSpec = tween(200),
                    targetScale = 0.8f
                )
            }
        ) { favourite ->
            if (favourite) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "add to favourite",
                    tint = Color.Red
//                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "remove from favourites",
                    tint = Color.White
//                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
            }
        }
    }
}