package com.example.movplayv3.ui.components.others

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayAppBar(
//    title: String?,
    modifier: Modifier = Modifier,
    scrollState: ScrollState? = null,
    transparentScrollValueLimit: Float? = null,
    backgroundColor: Color = Color.Black,
    action: @Composable () -> Unit = {},
    trailing: @Composable () -> Unit = {}
) {
    val alphaDelta = 1f - backgroundColor.alpha

    val currentScrollValue = scrollState?.value

    val alpha = if (currentScrollValue != null && transparentScrollValueLimit != null) {
        (backgroundColor.alpha + (currentScrollValue / transparentScrollValueLimit) * alphaDelta).coerceIn(
            backgroundColor.alpha,
            1f
        )
    } else {
        backgroundColor.alpha
    }

    Row(
        modifier = modifier
            .background(backgroundColor.copy(alpha))
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            action()
//            Spacer(modifier = androidx.compose.ui.Modifier.width(MaterialTheme.spacing.small))
//            title?.let {
//                Text(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = MaterialTheme.spacing.medium),
//                    text = it,
//                    style = MaterialTheme.typography.titleLarge,
//                    fontWeight = FontWeight.SemiBold,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
            trailing()
        }
    }
}