package com.example.movplayv3.ui.components.dropdowns

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.data.model.SortType

@Composable
fun SortTypeDropdown(
    expanded: Boolean,
    selectedSortType: SortType,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onSortTypeSelected: (SortType) -> Unit = {}
) {
    val items = SortType.values().map { type -> type to type.getLabelResId() }

    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        items.forEach { (type, labelResId) ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(labelResId),
                        color = if (type == selectedSortType) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    )
                },
                onClick = { onSortTypeSelected(type) }
            )
        }
    }

}