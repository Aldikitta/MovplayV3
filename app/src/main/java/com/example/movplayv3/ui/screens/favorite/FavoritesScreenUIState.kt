package com.example.movplayv3.ui.screens.favorite

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.movplayv3.data.model.FavoriteType
import com.example.movplayv3.data.model.Presentable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class FavoritesScreenUIState(
    val selectedFavouriteType: FavoriteType,
    val favourites: Flow<PagingData<Presentable>>,
) {
    companion object {
        val default: FavoritesScreenUIState = FavoritesScreenUIState(
            selectedFavouriteType = FavoriteType.Movie,
            favourites = emptyFlow()
        )
    }
}