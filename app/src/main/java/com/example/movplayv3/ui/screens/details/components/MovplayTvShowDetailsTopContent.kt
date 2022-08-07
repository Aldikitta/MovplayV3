package com.example.movplayv3.ui.screens.details.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.R
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.ui.components.texts.MovplayLabeledText
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayTvShowDetailsTopContent(
    tvShowDetails: TvShowDetails?,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = tvShowDetails
    ) { details ->
        if (details != null) {
            Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
                tvShowDetails?.let { details ->
                    MovplayLabeledText(
                        label = stringResource(R.string.tv_series_details_type),
                        text = stringResource(details.type.getLabel())
                    )
                    MovplayLabeledText(
                        label = stringResource(R.string.tv_series_details_status),
                        text = stringResource(details.status.getLabel())
                    )

                    MovplayLabeledText(
                        label = stringResource(R.string.tv_series_details_in_production),
                        text = stringResource(if (details.inProduction) R.string.yes else R.string.no)
                    )
                }
            }
        }
    }
}