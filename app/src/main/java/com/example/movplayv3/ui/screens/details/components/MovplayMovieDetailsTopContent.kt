package com.example.movplayv3.ui.screens.details.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.R
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.ui.components.texts.MovplayLabeledText
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.formattedMoney

@Composable
fun MovplayMovieDetailsTopContent(
    movieDetails: MovieDetails?,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = movieDetails
    ) { details ->
        if (details != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                MovplayLabeledText(
                    label = stringResource(R.string.movie_details_status),
                    text = stringResource(details.status.getLabel())
                )

                if (details.budget > 0) {
                    MovplayLabeledText(
                        label = stringResource(R.string.movie_details_budget),
                        text = details.budget.formattedMoney()
                    )
                }
                if (details.revenue > 0) {
                    MovplayLabeledText(
                        label = stringResource(R.string.movie_details_boxoffice),
                        text = details.revenue.formattedMoney()
                    )
                }
            }
        }
    }
}