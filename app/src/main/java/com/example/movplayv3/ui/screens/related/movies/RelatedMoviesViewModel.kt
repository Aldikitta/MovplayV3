package com.example.movplayv3.ui.screens.related.movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.movie.GetRelatedMoviesOfTypeUseCaseImpl
import com.example.movplayv3.ui.screens.destinations.RelatedMoviesScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RelatedMoviesViewModel @Inject constructor(
    private val getDeviceLanguageUseCaseImpl: GetDeviceLanguageUseCaseImpl,
    private val getRelatedMoviesOfTypeUseCase: GetRelatedMoviesOfTypeUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val navArgs: RelatedMoviesScreenArgs =
        RelatedMoviesScreenDestination.argsFrom(savedStateHandle)
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCaseImpl()

    val uiState: StateFlow<RelatedMoviesScreenUiState> =
        deviceLanguage.mapLatest { deviceLanguage ->
            val movies = getRelatedMoviesOfTypeUseCase(
                movieId = navArgs.movieId,
                type = navArgs.type,
                deviceLanguage = deviceLanguage
            ).cachedIn(viewModelScope)

            RelatedMoviesScreenUiState(
                relationType = navArgs.type,
                movies = movies,
                startRoute = navArgs.startRoute
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            RelatedMoviesScreenUiState.getDefault(navArgs.type)
        )
}