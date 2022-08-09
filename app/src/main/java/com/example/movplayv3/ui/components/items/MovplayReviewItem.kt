package com.example.movplayv3.ui.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoPhotography
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.movplayv3.R
import com.example.movplayv3.data.model.Review
import com.example.movplayv3.ui.components.texts.MovplayExpandableText
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.formatted

@Composable
fun MovplayReviewItem(
    review: Review,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium
) {
    val avatarPath = review.authorDetails.avatarPath

    val avatarUrl = when {
        avatarPath == null -> null
        avatarPath.startsWith("/") -> {
            avatarPath.removePrefix("/")
        }
        else -> review.authorDetails.avatarUrl
    }

    val score = review.authorDetails.rating

    Card(
        modifier = modifier,
        shape = shape,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (avatarUrl != null) {
                    Box {
                        Image(
                            modifier = Modifier.size(48.dp),
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current).data(data = avatarUrl)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        size(Size.ORIGINAL)
                                        scale(Scale.FILL)
                                        transformations(CircleCropTransformation())
                                        crossfade(true)
                                    }).build()
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.NoPhotography,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = MaterialTheme.spacing.small)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = review.authorDetails.username,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    review.createdAt?.let { date ->
                        Text(
                            text = date.formatted(),
                            fontSize = 12.sp
                        )
                    }
                }

                score?.let { score ->
                    MovplayPresentableScoreItem(
                        score = score,
                        animationEnabled = false
                    )
                }
            }

            MovplayExpandableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.medium),
                text = review.content,
                minLines = 6
            )

            Column(modifier = Modifier.align(Alignment.End)) {
                review.updatedAt?.let { date ->
                    Text(
                        modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                        text = stringResource(
                            R.string.review_item_last_modified_text,
                            date.formatted()
                        ),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}