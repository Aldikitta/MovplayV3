package com.example.movplayv3.ui.screens.details.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movplayv3.data.model.PersonDetails
import com.example.movplayv3.ui.components.texts.MovplayExpandableText
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayPersonDetailsInfoSection(
    personDetails: PersonDetails?,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = personDetails
    ) { details ->
        if (details != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.medium),
                    text = details.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                if (details.biography.isNotEmpty()) {
                    MovplayExpandableText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.medium),
                        text = details.biography
                    )
                }
            }
        }
    }

}