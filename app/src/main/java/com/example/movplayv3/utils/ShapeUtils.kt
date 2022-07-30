package com.example.movplayv3.utils

import androidx.annotation.FloatRange
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class BottomRoundedArcShape(
    @FloatRange(from = 0.0, to = 1.0)
    private val ratio: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0)
    private val edgeHeightRatio: Float = 0.9f,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawArcPath(
                size = size,
                ratio = ratio,
                edgeHeightRatio = edgeHeightRatio
            )
        )
    }
}

fun drawArcPath(size: Size, ratio: Float, edgeHeightRatio: Float): Path {
    val deltaHeight = 1f - edgeHeightRatio

    val edgePointsHeight = (edgeHeightRatio + deltaHeight * ratio) * size.height

    return Path().apply {
        reset()

        lineTo(size.width, 0f)
        lineTo(size.width, edgePointsHeight)
        cubicTo(
            x1 = size.width,
            y1 = edgePointsHeight,
            x2 = size.width / 2,
            y2 = size.height,
            x3 = 0f,
            y3 = edgePointsHeight
        )
        lineTo(0f, 0f)

        close()
    }
}