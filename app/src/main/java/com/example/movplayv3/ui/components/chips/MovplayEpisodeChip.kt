package com.example.movplayv3.ui.components.chips

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movplayv3.R
import com.example.movplayv3.data.model.Episode
import com.example.movplayv3.data.model.Image
import com.example.movplayv3.ui.components.others.MovplayStillBrowser
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.formatted

@SuppressLint("UnrememberedMutableState")
@Composable
fun MovplayEpisodeChip(
    episode: Episode,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    enabled: Boolean = true,
    stills: List<Image>? = null,
    onClick: () -> Unit = {}
) {
    val iconRotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
    val borderColor by animateColorAsState(
        targetValue = if (enabled) {
            MaterialTheme.colorScheme.primary.copy(0.5f)
        } else Color.Gray
    )

    //Stills equal to null means request is in progress
    val hasAdditionalContent by derivedStateOf {
        episode.run {
            overview.isNotBlank() || stills == null || stills.isNotEmpty()
        }
    }

    Card(
        modifier = modifier.clickable(
            enabled = enabled,
            onClick = { onClick.invoke() }
        ),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(width = 1.dp, color = borderColor),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = episode.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    episode.airDate?.let { date ->
                        Text(
                            text = date.formatted(),
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                        )
                    }

                    if (episode.voteCount != 0) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "rating",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(16.dp)
                                )

                                Text(
                                    text = stringResource(
                                        R.string.rating,
                                        episode.voteAverage,
                                        10
                                    ),
                                    fontSize = 12.sp
                                )
                            }

                            Text(
                                text = stringResource(R.string.vote_count, episode.voteCount),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                Spacer(modifier = androidx.compose.ui.Modifier.width(MaterialTheme.spacing.medium))
                AnimatedVisibility(visible = enabled) {
                    Icon(
                        modifier = Modifier.rotate(iconRotation),
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = if (expanded) "collapse" else "expand"
                    )
                }
            }

            AnimatedVisibility(
                enter = fadeIn(),
                exit = fadeOut(),
                visible = expanded
            ) {
                Crossfade(
                    modifier = Modifier.fillMaxWidth(),
                    targetState = hasAdditionalContent
                ) { hasContent ->
                    if (hasContent) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                        ) {
                            if (episode.overview.isNotBlank()) {
                                Text(text = episode.overview, fontSize = 12.sp)

                            }

                            stills?.let { stills ->
                                if (stills.isNotEmpty()) {
                                    MovplayStillBrowser(
                                        modifier = Modifier.fillMaxWidth(),
                                        stillPaths = stills
                                    )
                                }
                            }
                        }
                    } else {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.episode_chip_no_info_text),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}