package com.example.movplayv3.ui.components.selectors

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.movplayv3.R
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.createDateDialog
import com.example.movplayv3.utils.formatted
import java.util.*

@Composable
fun MovplayDateRangeSelector(
    fromDate: Date?,
    toDate: Date?,
    modifier: Modifier = Modifier,
    onFromDateChanged: (Date) -> Unit = {},
    onToDateChanged: (Date) -> Unit = {},
    onFromDateClearClicked: () -> Unit = {},
    onToDateClearClicked: () -> Unit = {}
) {
    val spacing = MaterialTheme.spacing.medium

    ConstraintLayout(modifier = modifier) {
        val (fromLabel, fromDateChip, arrowIcon, toLabel, toDateChip) = createRefs()

        Icon(
            modifier = Modifier.constrainAs(arrowIcon) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(fromDateChip.top)
                bottom.linkTo(fromDateChip.bottom)
            },
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        DateChip(
            modifier = Modifier
                .constrainAs(fromDateChip) {
                    top.linkTo(fromLabel.bottom, margin = 4.dp)
                    linkTo(
                        start = parent.start,
                        end = arrowIcon.start,
                        bias = 0f,
                        endMargin = spacing
                    )
                    width = Dimension.fillToConstraints
                },
            initialDate = fromDate,
            maxDate = toDate,
            onDateChanged = onFromDateChanged,
            onClearClicked = onFromDateClearClicked
        )

        DateChip(
            modifier = Modifier
                .constrainAs(toDateChip) {
                    top.linkTo(toLabel.bottom, margin = 4.dp)
                    linkTo(
                        start = arrowIcon.end,
                        end = parent.end,
                        bias = 1f,
                        startMargin = spacing
                    )
                    width = Dimension.fillToConstraints
                },
            initialDate = toDate,
            minDate = fromDate,
            onDateChanged = onToDateChanged,
            onClearClicked = onToDateClearClicked
        )

        Text(
            modifier = Modifier
                .constrainAs(fromLabel) {
                    top.linkTo(parent.top)
                    start.linkTo(fromDateChip.start)
                    bottom.linkTo(fromDateChip.top)
                },
            text = stringResource(R.string.date_range_selector_from_label),
            fontWeight = FontWeight.SemiBold
        )

        Text(
            modifier = Modifier
                .constrainAs(toLabel) {
                    top.linkTo(parent.top)
                    start.linkTo(toDateChip.start)
                    bottom.linkTo(toDateChip.top)
                },
            text = stringResource(R.string.date_range_selector_to_label),
            fontWeight = FontWeight.SemiBold
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DateChip(
    initialDate: Date?,
    modifier: Modifier = Modifier,
    minDate: Date? = null,
    maxDate: Date? = null,
    onDateChanged: (Date) -> Unit = {},
    onClearClicked: () -> Unit = {}
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clickable {
                createDateDialog(
                    context = context,
                    initialDate = initialDate,
                    onDateSelected = onDateChanged,
                    minDate = minDate,
                    maxDate = maxDate
                ).show()
            }
            .background(
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                shape = MaterialTheme.shapes.small
            )
            .padding(MaterialTheme.spacing.small),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.EditCalendar,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(
                text = initialDate?.formatted()
                    ?: stringResource(R.string.date_range_selector_select_hint),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (initialDate != null) Color.White else Color.White.copy(0.5f)
            )
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = initialDate != null,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onClearClicked() },
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null
                )
            }
        }
    }
}