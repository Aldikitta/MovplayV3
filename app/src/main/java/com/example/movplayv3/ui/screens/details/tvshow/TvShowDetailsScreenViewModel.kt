package com.example.movplayv3.ui.screens.details.tvshow

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.tvshow.*
import com.example.movplayv3.ui.screens.destinations.TvShowDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
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
    private val unlikeTvSeriesUseCase: UnlikeTvShowUseCaseImpl,
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
}