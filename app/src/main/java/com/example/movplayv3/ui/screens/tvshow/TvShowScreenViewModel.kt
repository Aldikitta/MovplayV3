package com.example.movplayv3.ui.screens.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.domain.usecase.interfaces.GetDeviceLanguageUseCase
import com.example.movplayv3.domain.usecase.interfaces.tvshow.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TvShowScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCase,
    private val getOnTheAirTvShowsUseCase: GetOnTheAirTvShowsUseCase,
    private val getDiscoverAllTvShowsUseCase: GetDiscoverAllTvShowsUseCase,
    private val getTopRatedTvShowsUseCase: GetTopRatedTvShowsUseCase,
    private val getTrendingTvShowsUseCase: GetTrendingTvShowsUseCase,
    private val getAiringTodayTvShowsUseCase: GetAiringTodayTvShowsUseCase,
    private val getFavoritesTvShowsUseCase: GetFavoritesTvShowsUseCase,
    private val getRecentlyBrowsedTvShowsUseCase: GetRecentlyBrowsedTvShowsUseCase
) : ViewModel() {
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()

    private val tvShowsState: StateFlow<TvShowsState> = deviceLanguage.mapLatest { deviceLanguage ->
        TvShowsState(
            onTheAir = getOnTheAirTvShowsUseCase(deviceLanguage, true)
                .cachedIn(viewModelScope),
            discover = getDiscoverAllTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            topRated = getTopRatedTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            trending = getTrendingTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            airingToday = getAiringTodayTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), TvShowsState.default)

    val uiState: StateFlow<TvShowScreenUIState> = tvShowsState.mapLatest { tvShowsState ->
        TvShowScreenUIState(
            tvShowsState = tvShowsState,
            favorites = getFavoritesTvShowsUseCase(),
            recentlyBrowsed = getRecentlyBrowsedTvShowsUseCase()
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, TvShowScreenUIState.default)
}