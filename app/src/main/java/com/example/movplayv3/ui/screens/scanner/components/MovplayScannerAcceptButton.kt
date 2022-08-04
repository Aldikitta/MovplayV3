package com.example.movplayv3.ui.screens.scanner.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun MovplayScannerAcceptButton(
    isScanningInProgress: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressedState by interactionSource.collectIsPressedAsState()

    val buttonScale by animateFloatAsState(targetValue = if (pressedState) 1.2f else 1f)

    val backgroundColor by animateColorAsState(
        targetValue = if (isScanningInProgress) {
            Color.Gray
        } else MaterialTheme.colorScheme.primary
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (isScanningInProgress) {
            false -> {
                Canvas(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = buttonScale
                            scaleY = buttonScale
                        }
                        .clip(CircleShape)
                        .fillMaxSize()
                        .clickable(
                            enabled = true,
                            onClick = onClick,
                            interactionSource = interactionSource,
                            indication = LocalIndication.current
                        )
                ) {
                    drawCircle(
                        radius = size.minDimension / 2.0f - 8.dp.toPx(),
                        color = backgroundColor
                    )
                    drawCircle(
                        radius = size.minDimension / 2.0f,
                        color = backgroundColor,
                        style = Stroke(width = 4.dp.toPx())
                    )
                }
            }
            true -> {
                CircularProgressIndicator()
            }
        }
    }
}