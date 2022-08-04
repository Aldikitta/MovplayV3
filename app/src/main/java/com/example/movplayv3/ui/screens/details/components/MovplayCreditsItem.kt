package com.example.movplayv3.ui.screens.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoPhotography
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.size.Scale
import com.example.movplayv3.ui.theme.sizes
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.ImageUrlParser
import com.example.movplayv3.utils.TmdbImage

@Composable
fun MovplayCreditsItem(
    posterPath: String?,
    title: String?,
    infoText: String?,
    modifier: Modifier = Modifier,
    size: com.example.movplayv3.ui.theme.Size = MaterialTheme.sizes.presentableItemSmall,
    onClick: () -> Unit = {}
) {
    var infoTextExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.width(size.width),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        Card(
            modifier = modifier
                .width(size.width)
                .height(size.height)
                .clickable { onClick() },
            shape = MaterialTheme.shapes.medium
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (posterPath != null) {
                    TmdbImage(
                        modifier = modifier.fillMaxSize(),
                        imagePath = posterPath,
                        imageType = ImageUrlParser.ImageType.Profile
                    ) {
                        size(coil.size.Size.ORIGINAL)
                        scale(Scale.FILL)
                        crossfade(true)
                    }
                } else {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surface)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                shape = MaterialTheme.shapes.medium
                            ),
//                        contentAlignment = Alignment.Center
                    ) {
                        Image(imageVector = Icons.Filled.NoPhotography, contentDescription = null)
                    }
                }
                title?.let { title ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(MaterialTheme.spacing.extraSmall),
                            text = title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
        infoText?.let { text ->
            if (text.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            infoTextExpanded = !infoTextExpanded
                        },
                    text = text,
//                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = if (infoTextExpanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}