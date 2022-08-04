package com.example.movplayv3.ui.components.sections

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movplayv3.data.model.Genre
import com.example.movplayv3.ui.components.chips.MovplayGenreChip
import com.example.movplayv3.ui.theme.spacing
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovplayGenresSection(
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        mainAxisSpacing = MaterialTheme.spacing.extraSmall,
        crossAxisSpacing = MaterialTheme.spacing.extraSmall
    ) {
        genres.map { genre ->
            MovplayGenreChip(text = genre.name)
        }
    }
}