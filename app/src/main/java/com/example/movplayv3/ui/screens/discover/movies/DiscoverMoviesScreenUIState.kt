package com.example.movplayv3.ui.screens.discover.movies

import androidx.compose.runtime.Stable
import com.example.movplayv3.data.model.SortOrder
import com.example.movplayv3.data.model.SortType

@Stable
class DiscoverMoviesScreenUIState(
    val sortInfo: SortInfo
) {
}

@Stable
data class SortInfo(
    val sortType: SortType,
    val sortOrder: SortOrder
) {
    companion object {
        val default: SortInfo = SortInfo(
            sortType = SortType.Popularity,
            sortOrder = SortOrder.Desc
        )
    }
}