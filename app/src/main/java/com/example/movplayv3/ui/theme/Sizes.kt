package com.example.movplayv3.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Sizes(
    val presentableItemSmall: Size = Size(120.dp, 180.dp),
    val presentableItemBig: Size = Size(160.dp, 240.dp),
    val videoItem: Size = Size(200.dp, 110.dp)
)

data class Size(val width: Dp, val height: Dp) {
    val ratio: Float = width / height
}

val LocalSizes = compositionLocalOf { Sizes() }

val MaterialTheme.sizes: Sizes
    @Composable
    @ReadOnlyComposable
    get() = LocalSizes.current