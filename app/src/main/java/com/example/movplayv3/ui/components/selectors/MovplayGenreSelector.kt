package com.example.movplayv3.ui.components.selectors

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movplayv3.data.model.Genre
import com.example.movplayv3.ui.theme.spacing
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovplayGenresSelector(
    genres: List<Genre>,
    selectedGenres: List<Genre>,
    modifier: Modifier = Modifier,
    onGenreClick: (Genre) -> Unit = {}
) {
    FlowRow(
        modifier = modifier,
        mainAxisSpacing = MaterialTheme.spacing.extraSmall,
        crossAxisSpacing = MaterialTheme.spacing.extraSmall
    ) {
        genres.sortedBy { genre ->
            genre.name
        }.map { genre ->
            FilterChip(
                modifier = Modifier.animateContentSize(),
                selected = genre in selectedGenres,
                onClick = { onGenreClick(genre) },
                label = {
                    Text(genre.name)
                },
                leadingIcon = if (genre in selectedGenres) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Localized Description",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                }
            )
        }
    }
}