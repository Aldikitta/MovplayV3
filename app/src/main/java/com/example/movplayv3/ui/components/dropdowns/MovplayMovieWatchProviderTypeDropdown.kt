package com.example.movplayv3.ui.components.dropdowns

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.data.model.movie.MovieWatchProviderType

@Composable
fun MovplayMovieWatchProviderTypeDropdown(
    expanded: Boolean,
    availableTypes: List<MovieWatchProviderType>,
    selectedType: MovieWatchProviderType,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onTypeSelected: (MovieWatchProviderType) -> Unit = {}
) {
    val items = availableTypes.map { type -> type to type.getLabelResId() }
    if (items.isNotEmpty()) {
        DropdownMenu(
            modifier = modifier,
            expanded = expanded,
            onDismissRequest = onDismissRequest
        ) {
            items.forEach { (type, labelResId) ->
                DropdownMenuItem(
                    onClick = { onTypeSelected(type) },
                    text = {
                        Text(
                            text = stringResource(labelResId),
                            color = if (type == selectedType) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    },
                    trailingIcon = {
                        if (type == selectedType)
                        Icon(imageVector = Icons.Filled.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                )
            }
        }
    }
}