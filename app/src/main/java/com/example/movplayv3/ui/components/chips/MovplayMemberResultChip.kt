package com.example.movplayv3.ui.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoPhotography
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.transform.CircleCropTransformation
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.ImageUrlParser
import com.example.movplayv3.utils.TmdbImage

@Composable
fun MovplayMemberResultChip(
    profilePath: String?,
    firstLine: String?,
    secondLine: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    var secondLineExpanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (profilePath != null) {
            TmdbImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape
                    )
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .clickable { onClick() },
                imagePath = profilePath,
                imageType = ImageUrlParser.ImageType.Profile
            ) {
                transformations(CircleCropTransformation())
                crossfade(true)
            }
        } else {
            MemberNoPhotoChip(onClick = onClick)
        }
        firstLine?.let { firstLine ->
            if (firstLine.isNotBlank()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = firstLine,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
        secondLine?.let { secondLine ->
            if (secondLine.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            secondLineExpanded = !secondLineExpanded
                        },
                    text = secondLine,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = if (secondLineExpanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun MemberNoPhotoChip(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        )
    }

}