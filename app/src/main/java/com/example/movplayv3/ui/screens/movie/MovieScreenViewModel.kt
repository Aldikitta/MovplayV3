package com.example.movplayv3.ui.screens.movie

import androidx.lifecycle.ViewModel
import com.example.movplayv3.domain.usecase.interfaces.GetDeviceLanguageUseCase
import com.example.movplayv3.domain.usecase.interfaces.movie.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val favoritesMoviesUseCase: GetFavoritesMoviesUseCase,
    private val getRecentlyBrowsedMoviesUseCase: GetRecentlyBrowsedMoviesUseCase
) : ViewModel() {
}