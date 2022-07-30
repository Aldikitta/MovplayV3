package com.example.movplayv3.ui.components.sections

import android.annotation.SuppressLint
import android.util.Size
import androidx.compose.animation.Animatable
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImagePainter
import com.example.movplayv3.R
import com.example.movplayv3.data.model.DetailPresentable
import com.example.movplayv3.ui.theme.sizes
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.max

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
    var isDark by rememberSaveable { mutableStateOf(true) }
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
                val backgroundPainterState = backgroundPainter.state

                LaunchedEffect(backgroundPainterState) {
                    if (backgroundPainterState is AsyncImagePainter.State.Success) {
                        isDark = backgroundPainterState.result.drawable.toBitmap().run { isDark() }

                        backdropScale.animateTo(
                            targetValue = 1.6f,
                            animationSpec = tween(durationMillis = 10000, easing = LinearEasing)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .matchParentSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        )
                )
                Image(
                    modifier = Modifier
                        .blur(8.dp)
                        .fillMaxSize()
                        .scale(backdropScale.value)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        ),
                    painter = backgroundPainter,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    color = contentColor,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                if (showMoreButton) {
                    TextButton(onClick = onMoreClick) {
                        Text(
                            text = stringResource(R.string.movies_more),
                            color = contentColor,
                            fontSize = 12.sp
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                }
            }
            HorizontalPager(
                count = max(state.itemCount, 1),
                state = pagerState
            ) {

            }
        }
    }
}