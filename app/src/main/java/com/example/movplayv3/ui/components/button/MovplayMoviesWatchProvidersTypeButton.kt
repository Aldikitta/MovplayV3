package com.example.movplayv3.ui.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movplayv3.data.model.movie.MovieWatchProviderType
import com.example.movplayv3.ui.components.dropdowns.MovplayMovieWatchProviderTypeDropdown

@Composable
fun MovplayMoviesWatchProvidersTypeButton(
    availableTypes: List<MovieWatchProviderType>,
    selectedType: MovieWatchProviderType,
    modifier: Modifier = Modifier,
    onTypeSelected: (MovieWatchProviderType) -> Unit = {}
) {
    var showMovieWatchProviderTypeDropdown by remember { mutableStateOf(false) }
    Box(modifier = modifier.wrapContentSize(Alignment.TopEnd)) {
        TextButton(
            onClick = { showMovieWatchProviderTypeDropdown = true }
        ) {
            Text(
                text = stringResource(selectedType.getLabelResId()),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null
            )
        }

        MovplayMovieWatchProviderTypeDropdown(
            expanded = showMovieWatchProviderTypeDropdown,
            availableTypes = availableTypes,
            onDismissRequest = {
                showMovieWatchProviderTypeDropdown = false
            },
            selectedType = selectedType,
            onTypeSelected = { type ->
                showMovieWatchProviderTypeDropdown = false

                if (type != selectedType) {
                    onTypeSelected(type)
                }
            }
        )
    }
}