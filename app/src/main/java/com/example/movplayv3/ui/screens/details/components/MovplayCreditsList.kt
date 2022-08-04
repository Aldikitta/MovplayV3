package com.example.movplayv3.ui.screens.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movplayv3.data.model.CreditPresentable
import com.example.movplayv3.data.model.MediaType
import com.example.movplayv3.ui.components.texts.MovplaySectionLabel
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayCreditsList(
    title: String,
    credits: List<CreditPresentable>,
    modifier: Modifier = Modifier,
    onCreditsClick: (MediaType, Int) -> Unit = { _, _ -> }
) {
    val creditsGroups = credits.groupBy { credit -> credit.id }.toList()

    if (credits.isNotEmpty()) {
        Column(modifier = modifier) {
            MovplaySectionLabel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.medium),
                text = title
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.small),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium)
            ) {
                items(creditsGroups) { (id, credits) ->
                    val posterPath = credits.firstNotNullOfOrNull { member -> member.posterPath }
                    val mediaType = credits.firstOrNull()?.mediaType
                    val mediaTitle = credits.firstNotNullOfOrNull { member -> member.title }
                    val infoText = credits.mapNotNull { member ->
                        member.infoText
                    }.joinToString(separator = ", ")

                    MovplayCreditsItem(
                        posterPath = posterPath,
                        title = mediaTitle,
                        infoText = infoText,
                        onClick = {
                            mediaType?.let { type ->
                                onCreditsClick(type, id)
                            }
                        }
                    )
                }
            }
        }
    }
}