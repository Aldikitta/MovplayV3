package com.example.movplayv3.ui.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movplayv3.ui.theme.DarkRed
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayAdultChips(
    modifier: Modifier = Modifier
) {
    Box(
    modifier = modifier
    .background(
    color = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
    shape = MaterialTheme.shapes.small
    )
    .border(width = 1.dp, color = DarkRed, shape = MaterialTheme.shapes.small),
    contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            text = "18+",
            fontSize = 16.sp
        )
    }
}