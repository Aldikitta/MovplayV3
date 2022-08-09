package com.example.movplayv3.ui.components.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movplayv3.ui.components.texts.MovplayInfoText
import com.example.movplayv3.ui.theme.spacing


@Composable
fun MovplayExpandableSection(
    label: String,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    infoText: String? = null,
    onClick: () -> Unit = {},
    trailing: @Composable ColumnScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val iconRotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = MaterialTheme.spacing.medium)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = label,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                AnimatedVisibility(
                    visible = expanded
                ) {
                    infoText?.let { text ->
                        MovplayInfoText(
                            modifier = Modifier.fillMaxWidth(),
                            text = text
                        )
                    }
                }
            }

            Icon(
                modifier = Modifier.rotate(iconRotation),
                imageVector = Icons.Filled.KeyboardArrowDown,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = expanded
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }

        trailing()
    }
}