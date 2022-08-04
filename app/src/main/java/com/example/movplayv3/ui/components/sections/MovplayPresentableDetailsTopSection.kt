package com.example.movplayv3.ui.components.sections

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.movplayv3.data.model.DetailPresentable
import com.example.movplayv3.data.model.DetailPresentableItemState
import com.example.movplayv3.data.model.Image
import com.example.movplayv3.ui.components.items.MovplayDetailPresentableItem
import com.example.movplayv3.ui.components.others.MovplayAnimatedBackdrops
import com.example.movplayv3.ui.theme.sizes
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.BottomRoundedArcShape

@SuppressLint("UnrememberedMutableState")
@Composable
fun MovplayPresentableDetailsTopSection(
    presentable: DetailPresentable?,
    modifier: Modifier = Modifier,
    backdrops: List<Image> = emptyList(),
    scrollState: ScrollState? = null,
    scrollValueLimit: Float? = null,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val presentableItemState by derivedStateOf {
        presentable?.let { DetailPresentableItemState.Result(it) }
            ?: DetailPresentableItemState.Loading
    }

    val availableBackdropPaths by remember(backdrops) {
        mutableStateOf(
            backdrops.map { backdrops -> backdrops.filePath }
        )
    }

    val currentScrollValue = scrollState?.value

    val ratio = if (currentScrollValue != null && scrollValueLimit != null) {
        (currentScrollValue / scrollValueLimit).coerceIn(0f, 1f)
    } else 0f

    Box(modifier = modifier.clip(RectangleShape)) {
        MovplayAnimatedBackdrops(
            modifier = Modifier
                .blur(8.dp)
                .matchParentSize()
                .graphicsLayer {
                    clip = true
                    shape = BottomRoundedArcShape(
                        ratio = ratio
                    )
                },
            paths = availableBackdropPaths
        )

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
                .padding(top = 56.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium)
            ) {
                val (presentableRef, contentRef) = createRefs()

                MovplayDetailPresentableItem(
                    modifier = Modifier.constrainAs(presentableRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                    size = MaterialTheme.sizes.presentableItemBig,
                    showScore = true,
                    showTitle = false,
                    showAdult = true,
                    presentableState = presentableItemState
                )

                Column(
                    modifier = Modifier
                        .constrainAs(contentRef) {
                            start.linkTo(presentableRef.end)
                            end.linkTo(parent.end)
                            top.linkTo(presentableRef.top)
                            bottom.linkTo(presentableRef.bottom)

                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = MaterialTheme.spacing.medium)
                ) {
                    content()
                }
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        }
    }
}