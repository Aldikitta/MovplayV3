package com.example.movplayv3.ui.components.selectors

import androidx.compose.animation.animateContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movplayv3.data.model.Genre
import com.example.movplayv3.ui.components.chips.MovplaySelectableGenreChip
import com.example.movplayv3.ui.theme.spacing
import com.google.accompanist.flowlayout.FlowRow

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
            MovplaySelectableGenreChip(
                modifier = Modifier.animateContentSize(),
                text = genre.name,
                selected = genre in selectedGenres,
                onClick = { onGenreClick(genre) }
            )
        }
    }
}