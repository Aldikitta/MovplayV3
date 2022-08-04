package com.example.movplayv3.ui.components.chips

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovplayGenreChip(
    text: String,
) {
    SuggestionChip(
        onClick = {},
        label = { Text(text) },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer),
        border = null
    )
}