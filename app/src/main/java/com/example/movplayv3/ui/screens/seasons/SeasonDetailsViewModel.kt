package com.example.movplayv3.ui.screens.seasons

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.remote.api.onException
import com.example.movplayv3.data.remote.api.onFailure
import com.example.movplayv3.data.remote.api.onSuccess
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.GetEpisodeStillsUseCaseImpl
import com.example.movplayv3.domain.usecase.tvshow.GetSeasonCreditsUseCaseImpl
import com.example.movplayv3.domain.usecase.tvshow.GetSeasonDetailsUseCaseImpl
import com.example.movplayv3.domain.usecase.tvshow.GetSeasonsVideosUseCaseImpl
import com.example.movplayv3.ui.screens.destinations.SeasonDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonDetailsViewModel @Inject constructor(
    private val getDeviceLanguageUseCaseImpl: GetDeviceLanguageUseCaseImpl,
    private val getSeasonDetailsUseCase: GetSeasonDetailsUseCaseImpl,
    private val getSeasonsVideosUseCaseImpl: GetSeasonsVideosUseCaseImpl,
    private val getSeasonCreditsUseCase: GetSeasonCreditsUseCaseImpl,
    private val getEpisodeStillsUseCaseImpl: GetEpisodeStillsUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val navArgs: SeasonDetailsScreenArgs =
        SeasonDetailsScreenDestination.argsFrom(savedStateHandle)
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCaseImpl()

    private val seasonDetails: MutableStateFlow<SeasonDetails?> = MutableStateFlow(null)
    private val aggregatedCredits: MutableStateFlow<AggregatedCredits?> = MutableStateFlow(null)
    private val episodesStills: MutableStateFlow<Map<Int, List<Image>>> =
        MutableStateFlow(emptyMap())

    private val videos: MutableStateFlow<List<Video>?> = MutableStateFlow(null)

    val uiState: StateFlow<SeasonDetailsScreenUiState> = combine(
        seasonDetails, aggregatedCredits, episodesStills, videos, error
    ) { details, credits, stills, videos, error ->
        SeasonDetailsScreenUiState(
            startRoute = navArgs.startRoute,
            seasonDetails = details,
            aggregatedCredits = credits,
            videos = videos,
            episodeCount = details?.episodes?.count(),
            episodeStills = stills,
            error = error
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        SeasonDetailsScreenUiState.default
    )

    init {
        viewModelScope.launch {
            deviceLanguage.collectLatest { deviceLanguage ->
                launch {
                    getSeasonDetailsUseCase(
                        tvShowId = navArgs.tvShowId,
                        seasonNumber = navArgs.seasonNumber,
                        deviceLanguage = deviceLanguage
                    ).onSuccess {
                        viewModelScope.launch {
                            seasonDetails.emit(data)
                        }
                    }.onFailure {
                        onFailure(this)
                    }.onException {
                        onError(this)
                    }
                }

                launch {
                    getSeasonCredits(
                        tvShowId = navArgs.tvShowId,
                        seasonNumber = navArgs.seasonNumber,
                        deviceLanguage = deviceLanguage
                    )
                }

                launch {
                    getSeasonVideos(
                        tvShowId = navArgs.tvShowId,
                        seasonNumber = navArgs.seasonNumber,
                        deviceLanguage = deviceLanguage
                    )
                }
            }
        }
    }

    fun getEpisodeStills(episodeNumber: Int) {
        if (episodesStills.value.containsKey(episodeNumber)) {
            return
        }

        viewModelScope.launch {
            getEpisodeStillsUseCaseImpl(
                tvShowId = navArgs.tvShowId,
                seasonNumber = navArgs.seasonNumber,
                episodeNumber = episodeNumber
            ).onSuccess {
                viewModelScope.launch {
                    episodesStills.collectLatest { current ->
                        val updatedStills = current.toMutableMap().apply {
                            put(episodeNumber, data ?: emptyList())
                        }
                        episodesStills.emit(updatedStills)
                    }
                }
            }.onFailure {
                onFailure(this)
            }.onException {
                onError(this)
            }
        }
    }

    private suspend fun getSeasonCredits(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ) {
        getSeasonCreditsUseCase(
            tvShowId = tvShowId,
            seasonNumber = seasonNumber,
            deviceLanguage = deviceLanguage
        ).onSuccess {
            data?.let { credits ->
                viewModelScope.launch {
                    aggregatedCredits.emit(credits)
                }
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getSeasonVideos(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ) {
        getSeasonsVideosUseCaseImpl(
            tvShowId = tvShowId,
            seasonNumber = seasonNumber,
            deviceLanguage = deviceLanguage
        ).onSuccess {
            viewModelScope.launch {
                videos.emit(data ?: emptyList())
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }
}