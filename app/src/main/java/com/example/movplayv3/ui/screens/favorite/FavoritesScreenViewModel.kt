package com.example.movplayv3.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.data.model.FavoriteType
import com.example.movplayv3.domain.usecase.GetFavoritesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val getFavouritesUseCaseImpl: GetFavoritesUseCaseImpl
) : ViewModel() {
    private val _selectedFavouriteType: MutableStateFlow<FavoriteType> =
        MutableStateFlow(FavoriteType.Movie)

    val uiState: StateFlow<FavoritesScreenUIState> = _selectedFavouriteType.mapLatest { type ->
        val favorites = getFavouritesUseCaseImpl(type).cachedIn(viewModelScope)

        FavoritesScreenUIState(
            selectedFavouriteType = type,
            favorites = favorites
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, FavoritesScreenUIState.default)

    fun onFavoriteTypeSelected(type: FavoriteType) {
        viewModelScope.launch {
            _selectedFavouriteType.emit(type)
        }
    }
}