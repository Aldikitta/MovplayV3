package com.example.movplayv3.ui.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayGenreChip(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(50f),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                shape = RoundedCornerShape(50f)
            )
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.extraSmall
            )
            .clickable(enabled = onClick != null, onClick = { onClick?.invoke() })

    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold
        )
    }
}