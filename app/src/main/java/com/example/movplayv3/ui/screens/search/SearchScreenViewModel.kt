package com.example.movplayv3.ui.screens.search

import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.domain.usecase.interfaces.*
import com.example.movplayv3.domain.usecase.interfaces.movie.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCase,
    private val getSpeechToTextAvailableUseCase: GetSpeechToTextAvailableUseCase,
    private val getCameraAvailableUseCase: GetCameraAvailableUseCase,
    private val mediaAddSearchQueryUseCase: MediaAddSearchQueryUseCase,
    private val mediaSearchQueriesUseCase: MediaSearchQueriesUseCase,
    private val getMediaMultiSearchUseCase: GetMediaMultiSearchUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel() {
}