package com.example.movplayv3.ui.components.sections

import android.annotation.SuppressLint
import android.util.Size
import androidx.compose.animation.Animatable
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.paging.compose.LazyPagingItems
import com.example.movplayv3.data.model.DetailPresentable
import com.example.movplayv3.ui.theme.sizes
import com.example.movplayv3.utils.BottomRoundedArcShape
import com.example.movplayv3.utils.ImageUrlParser
import com.example.movplayv3.utils.getMaxSizeInt
import com.example.movplayv3.utils.rememberTmdbImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovplayPresentableTopSection(
    title: String,
    state: LazyPagingItems<out DetailPresentable>,
    modifier: Modifier = Modifier,
    showMoreButton: Boolean = true,
    scrollState: ScrollState? = null,
    scrollValueLimit: Float? = null,
    onPresentableClick: (Int) -> Unit = {},
    onMoreClick: () -> Unit = {}
) {
    val density = LocalDensity.current
    val pagerState = rememberPagerState()
    val isDark by rememberSaveable { mutableStateOf(true) }
    val contentColor by animateColorAsState(targetValue = if (isDark) Color.White else Color.Black)
    val selectedPresentable by derivedStateOf {
        val snapShot = state.itemSnapshotList
        if (snapShot.isNotEmpty()) snapShot.getOrNull(pagerState.currentPage) else null
    }
    val currentScrollValue = scrollState?.value
    val ratio = if (currentScrollValue != null && scrollValueLimit != null) {
        (currentScrollValue / scrollValueLimit).coerceIn(0f, 1f)
    } else 0f
    val itemHeight = density.run { MaterialTheme.sizes.presentableItemBig.height.toPx() }

    Box(modifier = Modifier.clip(RectangleShape)) {
        BoxWithConstraints(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    clip = true
                    shape = BottomRoundedArcShape(
                        ratio = ratio
                    )
                }
        ) {
            val (maxWidth, maxHeight) = getMaxSizeInt()
            Crossfade(
                targetState = selectedPresentable,
                modifier = Modifier.fillMaxSize()
            ) { movie ->
                val backdropScale = remember {
                    androidx.compose.animation.core.Animatable(1f)
                }
                val backgroundPainter = rememberTmdbImagePainter(
                    path = movie?.backdropPath,
                    type = ImageUrlParser.ImageType.Backdrop,
                    preferredSize = Size(maxWidth, maxHeight),
                    builder = {
                        allowHardware(false)
                    }
                )
                val backgroundPainterScale = backgroundPainter.state
            }
        }
    }
}