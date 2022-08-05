package com.example.movplayv3.ui.components.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.R
import com.example.movplayv3.ui.components.texts.MovplaySectionLabel
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayReviewSection(
    count: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(modifier = modifier
        .clickable { onClick() }
        .padding(
            vertical = MaterialTheme.spacing.small,
            horizontal = MaterialTheme.spacing.medium
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovplaySectionLabel(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.movie_details_reviews, count)
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}