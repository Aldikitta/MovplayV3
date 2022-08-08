package com.example.movplayv3.ui.components.selectors

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movplayv3.data.model.FavoriteType
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayFavoriteTypeSelector(
    selected: FavoriteType,
    modifier: Modifier = Modifier,
    onSelected: (FavoriteType) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        FavoriteType.values().map { type ->
            FavouriteTypeButton(
                type = type,
                selected = type == selected,
                onClick = { onSelected(type) }
            )
        }
    }
}


@Composable
fun FavouriteTypeButton(
    type: FavoriteType,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val textColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.primary
        } else Color.White
    )
    val lineLength = animateFloatAsState(
        targetValue = if (selected) 2f else 0f,
        animationSpec = tween(
            durationMillis = 200
        )
    )
    val currentOption = MaterialTheme.colorScheme.primary
    Text(
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
            .drawBehind {
                if (selected) {
                    if (lineLength.value > 0f) {
                        drawLine(
                            color = if (selected) {
                                currentOption
                            } else {
                                Color.Magenta
                            },
                            start = Offset(
                                size.width / 2f - lineLength.value * 10.dp.toPx(),
                                size.height
                            ),
                            end = Offset(
                                size.width / 2f + lineLength.value * 10.dp.toPx(),
                                size.height
                            ),
                            strokeWidth = 5.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }) {
                onClick()
            },
        text = stringResource(type.getLabelResourceId()),
        color = textColor,
        style = MaterialTheme.typography.headlineMedium,

        )
}