package com.example.movplayv3.ui.screens.discover.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.SortOrder
import com.example.movplayv3.data.model.SortType
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.movie.GetAllMoviesWatchProvidersUseCaseImpl
import com.example.movplayv3.domain.usecase.movie.GetDiscoverMoviesUseCaseImpl
import com.example.movplayv3.domain.usecase.movie.GetMovieGenresUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val getMovieGenresUseCase: GetMovieGenresUseCaseImpl,
    private val getAllMoviesWatchProvidersUseCase: GetAllMoviesWatchProvidersUseCaseImpl,
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCaseImpl
) : ViewModel() {
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()
    private val availableMovieGenres = getMovieGenresUseCase()
    private val availableWatchProviders = getAllMoviesWatchProvidersUseCase()

    private val sortInfo: MutableStateFlow<SortInfo> = MutableStateFlow(SortInfo.default)

    private val _filterState: MutableStateFlow<MovieFilterState> =
        MutableStateFlow(MovieFilterState.default)
    private val filterState: StateFlow<MovieFilterState> = combine(
        _filterState,
        availableMovieGenres,
        availableWatchProviders
    ) { filterState, genres, watchProviders ->
        filterState.copy(
            availableGenres = genres,
            availableWatchProviders = watchProviders
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, MovieFilterState.default)

    val uiState: StateFlow<DiscoverMoviesScreenUIState> = combine(
        deviceLanguage, sortInfo, filterState
    ) { deviceLanguage, sortInfo, filterState ->
        val movies = getDiscoverMoviesUseCase(
            sortInfo = sortInfo,
            filterState = filterState,
            deviceLanguage = deviceLanguage
        ).cachedIn(viewModelScope)

        DiscoverMoviesScreenUIState(
            sortInfo = sortInfo,
            filterState = filterState,
            movies = movies
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        DiscoverMoviesScreenUIState.default
    )

    fun onSortTypeChange(sortType: SortType) {
        viewModelScope.launch {
            val currentSortInfo = sortInfo.value

            sortInfo.emit(currentSortInfo.copy(sortType = sortType))
        }
    }

    fun onSortOrderChange(sortOrder: SortOrder) {
        viewModelScope.launch {
            val currentSortInfo = sortInfo.value

            sortInfo.emit(currentSortInfo.copy(sortOrder = sortOrder))
        }
    }

    fun onFilterStateChange(state: MovieFilterState) {
        viewModelScope.launch {
            _filterState.emit(state)
        }
    }
}