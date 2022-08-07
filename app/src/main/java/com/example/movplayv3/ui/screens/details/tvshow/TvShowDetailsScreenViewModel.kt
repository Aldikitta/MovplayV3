package com.example.movplayv3.ui.screens.details.tvshow

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.remote.api.onException
import com.example.movplayv3.data.remote.api.onFailure
import com.example.movplayv3.data.remote.api.onSuccess
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.tvshow.*
import com.example.movplayv3.ui.screens.destinations.TvShowDetailsScreenDestination
import com.example.movplayv3.ui.screens.details.movie.AssociatedContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val addRecentlyBrowsedTvShowUseCase: AddRecentlyBrowsedTvShowUseCaseImpl,
    private val getFavoriteTvShowIdsUseCase: GetFavoriteTvShowIdsUseCaseImpl,
    private val getRelatedTvShowsOfTypeUseCase: GetRelatedTvShowsOfTypeUseCaseImpl,
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCaseImpl,
    private val getNextEpisodeDaysRemainingUseCase: GetNextEpisodeDaysRemainingUseCaseImpl,
    private val getTvShowExternalIdsUseCase: GetTvShowExternalIdsUseCaseImpl,
    private val getTvShowImagesUseCase: GetTvShowImagesUseCaseImpl,
    private val getTvShowReviewsCountUseCase: GetTvShowReviewsCountUseCaseImpl,
    private val getTvShowVideosUseCase: GetTvShowVideosUseCaseImpl,
    private val getTvShowWatchProvidersUseCase: GetTvShowWatchProvidersUseCaseImpl,
    private val likeTvShowUseCase: LikeTvShowUseCaseImpl,
    private val unlikeTvShowUseCase: UnlikeTvShowUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val navArgs: TvShowDetailsScreenArgs =
        TvShowDetailsScreenDestination.argsFrom(savedStateHandle)

    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()
    private val favoriteTvShowIds: Flow<List<Int>> = getFavoriteTvShowIdsUseCase()

    private val _tvShowDetails: MutableStateFlow<TvShowDetails?> = MutableStateFlow(null)
    private val tvShowDetails: StateFlow<TvShowDetails?> =
        _tvShowDetails.onEach { tvShowDetails ->
            tvShowDetails?.let(addRecentlyBrowsedTvShowUseCase::invoke)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), null)

    private val tvShowBackdrops: MutableStateFlow<List<Image>> = MutableStateFlow(emptyList())
    private val videos: MutableStateFlow<List<Video>?> = MutableStateFlow(null)
    private val nextEpisodeDaysRemaining: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val watchProviders: MutableStateFlow<WatchProviders?> = MutableStateFlow(null)
    private val reviewsCount: MutableStateFlow<Int> = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val isFavorite: StateFlow<Boolean> = favoriteTvShowIds.mapLatest { favoriteIds ->
        navArgs.tvShowId in favoriteIds
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), false)

    private val externalIds: MutableStateFlow<List<ExternalId>?> = MutableStateFlow(null)

    private val additionalInfo: StateFlow<AdditionalTvShowDetailsInfo> = combine(
        isFavorite, nextEpisodeDaysRemaining, watchProviders, reviewsCount
    ) { isFavorite, nextEpisodeDaysRemaining, watchProviders, reviewsCount ->
        AdditionalTvShowDetailsInfo(
            isFavourite = isFavorite,
            nextEpisodeRemainingDays = nextEpisodeDaysRemaining,
            watchProviders = watchProviders,
            reviewsCount = reviewsCount
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(10),
        AdditionalTvShowDetailsInfo.default
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val associatedTvShow: StateFlow<AssociatedTvShow> =
        deviceLanguage.mapLatest { deviceLanguage ->
            AssociatedTvShow(
                similar = getRelatedTvShowsOfTypeUseCase(
                    tvShowId = navArgs.tvShowId,
                    type = RelationType.Similar,
                    deviceLanguage = deviceLanguage
                ).cachedIn(viewModelScope),
                recommendations = getRelatedTvShowsOfTypeUseCase(
                    tvShowId = navArgs.tvShowId,
                    type = RelationType.Recommended,
                    deviceLanguage = deviceLanguage
                ).cachedIn(viewModelScope)
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), AssociatedTvShow.default)

    private val associatedContent: StateFlow<AssociatedContent> = combine(
        tvShowBackdrops, videos, externalIds
    ) { backdrops, videos, externalIds ->
        AssociatedContent(
            backdrops = backdrops,
            videos = videos,
            externalIds = externalIds
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), AssociatedContent.default)

    val uiState: StateFlow<TvShowDetailsScreenUIState> = combine(
        tvShowDetails, additionalInfo, associatedTvShow, associatedContent, error
    ) { details, additionalInfo, associatedTvSeries, visualContent, error ->
        TvShowDetailsScreenUIState(
            startRoute = navArgs.startRoute,
            tvSeriesDetails = details,
            additionalTvSeriesDetailsInfo = additionalInfo,
            associatedTvSeries = associatedTvSeries,
            associatedContent = visualContent,
            error = error
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        TvShowDetailsScreenUIState.getDefault(navArgs.startRoute)
    )

    init {
        getTvShowsInfo()
    }

    fun onLikeClick(tvShowDetails: TvShowDetails) {
        likeTvShowUseCase(tvShowDetails)
    }

    fun onUnlikeClick(tvSeriesDetails: TvShowDetails) {
        unlikeTvShowUseCase(tvSeriesDetails)
    }

    private fun getTvShowsInfo() {
        val tvShowId = navArgs.tvShowId

        viewModelScope.launch {
            launch {
                getTvShowImages(tvShowId)
            }

            launch {
                getExternalIds(tvShowId)
            }

            launch {
                getTvShowReviewsCount(tvShowId)
            }

            deviceLanguage.collectLatest { deviceLanguage ->
                launch {
                    getTvShowDetails(
                        tvShowId = tvShowId,
                        deviceLanguage = deviceLanguage
                    )
                }

                launch {
                    getWatchProviders(
                        tvShowId = tvShowId,
                        deviceLanguage = deviceLanguage
                    )
                }

                launch {
                    getTvShowVideos(
                        tvShowId = tvShowId,
                        deviceLanguage = deviceLanguage
                    )
                }
            }
        }
    }

    private suspend fun getTvShowDetails(tvShowId: Int, deviceLanguage: DeviceLanguage) {
        getTvShowDetailsUseCase(
            tvShowId = tvShowId,
            deviceLanguage = deviceLanguage
        ).onSuccess {
            viewModelScope.launch {
                val tvShowDetails = data
                _tvShowDetails.emit(tvShowDetails)

                tvShowDetails?.nextEpisodeToAir?.airDate?.let { date ->
                    val daysRemaining = getNextEpisodeDaysRemainingUseCase(date)
                    nextEpisodeDaysRemaining.emit(daysRemaining)
                }
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getTvShowImages(tvShowId: Int) {
        getTvShowImagesUseCase(tvShowId)
            .onSuccess {
                viewModelScope.launch {
                    tvShowBackdrops.emit(data ?: emptyList())
                }
            }.onFailure {
                onFailure(this)
            }.onException {
                onError(this)
            }
    }

    private suspend fun getTvShowReviewsCount(tvShowId: Int) {
        getTvShowReviewsCountUseCase(tvShowId)
            .onSuccess {
                viewModelScope.launch {
                    reviewsCount.emit(data ?: 0)
                }
            }.onFailure {
                onFailure(this)
            }.onException {
                onError(this)
            }
    }

    private suspend fun getWatchProviders(tvShowId: Int, deviceLanguage: DeviceLanguage) {
        getTvShowWatchProvidersUseCase(
            tvShowId = tvShowId,
            deviceLanguage = deviceLanguage
        ).onSuccess {
            viewModelScope.launch {
                watchProviders.emit(data)
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getExternalIds(tvShowId: Int) {
        getTvShowExternalIdsUseCase(
            tvShowId = tvShowId
        ).onSuccess {
            viewModelScope.launch {
                externalIds.emit(data)
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getTvShowVideos(tvShowId: Int, deviceLanguage: DeviceLanguage) {
        getTvShowVideosUseCase(
            tvShowId = tvShowId,
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