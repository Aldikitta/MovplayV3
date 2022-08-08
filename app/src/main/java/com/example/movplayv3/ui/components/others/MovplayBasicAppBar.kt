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
import com.example.movplayv3.ui.theme.spacing

@Composable
fun MovplayBasicAppBar(
    title: String?,
    modifier: Modifier = Modifier,
    action: @Composable () -> Unit = {},
    trailing: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
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
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            title?.let {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = MaterialTheme.spacing.medium),
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            trailing()
        }
    }
}