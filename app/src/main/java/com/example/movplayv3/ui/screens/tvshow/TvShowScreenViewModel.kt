package com.example.movplayv3.ui.screens.tvshow

import androidx.lifecycle.ViewModel
import com.example.movplayv3.domain.usecase.interfaces.GetDeviceLanguageUseCase
import com.example.movplayv3.domain.usecase.interfaces.tvshow.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCaseImpl: GetDeviceLanguageUseCase,
    private val getOnTheAirTvShowsUseCase: GetOnTheAirTvShowsUseCase,
    private val getDiscoverAllTvShowsUseCase: GetDiscoverAllTvShowsUseCase,
    private val getTopRatedTvShowsUseCase: GetTopRatedTvShowsUseCase,
    private val getTrendingTvShowsUseCase: GetTrendingTvShowsUseCase,
    private val getAiringTodayTvShowsUseCase: GetAiringTodayTvShowsUseCase,
    private val getFavoriteTvShowsCountUseCase: GetFavoriteTvShowsCountUseCase,
    private val getRecentlyBrowsedTvShowsUseCase: GetRecentlyBrowsedTvShowsUseCase
) : ViewModel() {
}