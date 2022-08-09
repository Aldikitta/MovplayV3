package com.example.movplayv3.ui.screens.related.tvseries

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.tvshow.GetRelatedTvShowsOfTypeUseCaseImpl
import com.example.movplayv3.ui.screens.destinations.RelatedTvShowScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RelatedTvShowViewModel @Inject constructor(
    private val getDeviceLanguageUseCaseImpl: GetDeviceLanguageUseCaseImpl,
    private val getRelatedTvSeriesOfTypeUseCase: GetRelatedTvShowsOfTypeUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val navArgs: RelatedTvShowScreenArgs =
        RelatedTvShowScreenDestination.argsFrom(savedStateHandle)
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCaseImpl()

    val uiState: StateFlow<RelatedTvShowScreenUiState> =
        deviceLanguage.mapLatest { deviceLanguage ->
            val tvShow = getRelatedTvSeriesOfTypeUseCase(
                tvShowId = navArgs.tvShowId,
                type = navArgs.type,
                deviceLanguage = deviceLanguage
            ).cachedIn(viewModelScope)

            RelatedTvShowScreenUiState(
                relationType = navArgs.type,
                tvShow = tvShow,
                startRoute = navArgs.startRoute
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            RelatedTvShowScreenUiState.getDefault(navArgs.type)
        )
}