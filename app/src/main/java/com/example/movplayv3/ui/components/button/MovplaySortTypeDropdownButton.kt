package com.example.movplayv3.ui.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movplayv3.data.model.SortType
import com.example.movplayv3.ui.components.dropdowns.SortTypeDropdown

@Composable
fun MovplaySortTypeDropdownButton(
    selectedType: SortType,
    modifier: Modifier = Modifier,
    onTypeSelected: (SortType) -> Unit = {}
) {
    var showSortTypeDropdown by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(
            onClick = {
                showSortTypeDropdown = true
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Sort,
                contentDescription = null
            )
        }

        SortTypeDropdown(
            expanded = showSortTypeDropdown,
            onDismissRequest = {
                showSortTypeDropdown = false
            },
            selectedSortType = selectedType,
            onSortTypeSelected = { type ->
                showSortTypeDropdown = false

                if (type != selectedType) {
                    onTypeSelected(type)
                }
            }
        )
    }
}