package com.example.movplayv3.ui.screens.browse.movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.movie.ClearRecentlyBrowsedMoviesUseCaseImpl
import com.example.movplayv3.domain.usecase.movie.GetFavoritesMovieCountUseCaseImpl
import com.example.movplayv3.domain.usecase.movie.GetMoviesOfTypeUseCaseImpl
import com.example.movplayv3.ui.screens.destinations.BrowseMoviesScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class BrowseMoviesViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val getMoviesOfTypeUseCase: GetMoviesOfTypeUseCaseImpl,
    private val getFavoritesMoviesCountUseCase: GetFavoritesMovieCountUseCaseImpl,
    private val getClearRecentlyBrowsedMoviesUseCase: ClearRecentlyBrowsedMoviesUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()
    private val navArgs: BrowseMoviesScreenArgs =
        BrowseMoviesScreenDestination.argsFrom(savedStateHandle)
    private val favoriteMoviesCount: StateFlow<Int> = getFavoritesMoviesCountUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), 0)

    val uiState: StateFlow<BrowseMoviesScreenUIState> = combine(
        deviceLanguage, favoriteMoviesCount
    ) { deviceLanguage, favoriteMoviesCount ->
        val movies = getMoviesOfTypeUseCase(
            type = navArgs.movieType,
            deviceLanguage = deviceLanguage
        ).cachedIn(viewModelScope)

        BrowseMoviesScreenUIState(
            selectedMovieType = navArgs.movieType,
            movies = movies,
            favoriteMoviesCount = favoriteMoviesCount
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        BrowseMoviesScreenUIState.getDefault(navArgs.movieType)
    )

    fun onClearClicked() = getClearRecentlyBrowsedMoviesUseCase()
}