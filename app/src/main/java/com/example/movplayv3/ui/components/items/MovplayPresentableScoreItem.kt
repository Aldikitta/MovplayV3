package com.example.movplayv3.ui.components.items

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movplayv3.ui.theme.*

@Composable
fun MovplayPresentableScoreItem(
    score: Float,
    modifier: Modifier = Modifier,
    scoreRange: ClosedFloatingPointRange<Float> = 0f..10f,
    animationEnabled: Boolean = true,
    strokeWidth: Dp = 3.dp
) {
    val progress = score / scoreRange.run { endInclusive - start }

    val animatedProgress = remember { Animatable(0f) }

    val percent = (progress * 100).toInt()

    LaunchedEffect(progress, animationEnabled) {
        if (animationEnabled) {
            animatedProgress.animateTo(progress, animationSpec = tween(durationMillis = 700))
        } else {
            animatedProgress.snapTo(progress)
        }
    }

    val indicatorColor by animateColorAsState(
        targetValue = when (progress) {
            in 0f..0.15f -> DarkRed
            in 0.15f..0.3f -> Red
            in 0.3f..0.45f -> Orange
            in 0.45f..0.7f -> Yellow
            in 0.7f..0.85f -> LightGreen
            else -> DarkGreen
        }
    )

    val backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f)

    val scoreText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
        ) {
            append(percent.toString())
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
                color = Color.White
            )
        ) {
            append("%")
        }
    }

    Box(modifier = modifier.padding(strokeWidth / 2), contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .aspectRatio(1f)
        ) {
            drawCircle(color = backgroundColor)

            inset(
                horizontal = strokeWidth.toPx() / 2,
                vertical = strokeWidth.toPx() / 2
            ) {
                drawArc(
                    color = indicatorColor.copy(alpha = 0.3f),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth.toPx())
                )
                drawArc(
                    color = indicatorColor,
                    startAngle = -90f,
                    sweepAngle = animatedProgress.value * 360f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }

        }
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = scoreText,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 1
            )
        }
    }
}