package com.example.movplayv3.ui.components.sections

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.ui.theme.spacing
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovplayExternalIdsSection(
    externalIds: List<ExternalId>,
    modifier: Modifier = Modifier,
    onExternalIdClick: (ExternalId) -> Unit = {}
) {
    if (externalIds.isNotEmpty()) {
        FlowRow(
            modifier = modifier.wrapContentHeight(),
            mainAxisAlignment = FlowMainAxisAlignment.Start,
            crossAxisAlignment = FlowCrossAxisAlignment.Start,
            crossAxisSpacing = MaterialTheme.spacing.small,
            mainAxisSpacing = MaterialTheme.spacing.extraSmall
        ) {
            externalIds.map { id ->
                IdChip(drawableRes = id.drawableRes) {
                    onExternalIdClick(id)
                }
            }
        }
    }
}

@Composable
fun IdChip(
    @DrawableRes drawableRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(modifier = modifier
        .clip(CircleShape)
        .clickable { onClick() }
        .padding(MaterialTheme.spacing.extraSmall)
    ) {
        Image(
            painter = painterResource(drawableRes),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }

}