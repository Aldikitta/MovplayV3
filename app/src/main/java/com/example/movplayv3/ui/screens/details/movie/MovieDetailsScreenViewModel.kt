package com.example.movplayv3.ui.screens.details.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.MovieCollection
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.remote.api.onException
import com.example.movplayv3.data.remote.api.onFailure
import com.example.movplayv3.data.remote.api.onSuccess
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.movie.*
import com.example.movplayv3.ui.screens.destinations.MovieDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MovieDetailsScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val getRelatedMoviesUseCase: GetRelatedMoviesOfTypeUseCaseImpl,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCaseImpl,
    private val getMovieBackdropsUseCase: GetMovieBackdropsUseCaseImpl,
    private val getMovieExternalIdsUseCase: GetMovieExternalIdsUseCaseImpl,
    private val getMovieWatchProvidersUseCase: GetMovieWatchProvidersUseCaseImpl,
    private val getMovieReviewsCountUseCase: GetMovieReviewsCountUseCaseImpl,
    private val getMovieCreditsUseCase: GetMovieCreditUseCaseImpl,
    private val getMoviesVideosUseCase: GetMovieVideosUseCaseImpl,
    private val getMovieCollectionUseCase: GetMovieCollectionUseCaseImpl,
    private val getOtherDirectorMoviesUseCase: GetOtherDirectorMoviesUseCaseImpl,
    private val getFavoriteMoviesIdsUseCaseImpl: GetFavoriteMoviesIdsUseCaseImpl,
    private val addRecentlyBrowsedMovieUseCase: AddRecentlyBrowsedMovieUseCaseImpl,
    private val unlikeMovieUseCase: UnlikeMovieUseCaseImpl,
    private val likeMovieUseCaseImpl: LikeMovieUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val navArgs: MovieDetailsScreenArgs =
        MovieDetailsScreenDestination.argsFrom(savedStateHandle)
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()
    private val favoritesMoviesIdsFlow: Flow<List<Int>> = getFavoriteMoviesIdsUseCaseImpl()
    private val watchAtTime: MutableStateFlow<Date?> = MutableStateFlow(null)
    private val _movieDetails: MutableStateFlow<MovieDetails?> = MutableStateFlow(null)
    private val movieDetails: StateFlow<MovieDetails?> =
        _movieDetails.onEach { movieDetails ->
            movieDetails?.let(addRecentlyBrowsedMovieUseCase::invoke)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), null)
    private val credits: MutableStateFlow<Credits?> = MutableStateFlow(null)
    private val movieBackdrops: MutableStateFlow<List<Image>> = MutableStateFlow(emptyList())
    private val movieCollection: MutableStateFlow<MovieCollection?> = MutableStateFlow(null)
    private val otherDirectorMovies: MutableStateFlow<DirectorMovies> = MutableStateFlow(
        DirectorMovies.default
    )
    private val watchProviders: MutableStateFlow<WatchProviders?> = MutableStateFlow(null)
    private val videos: MutableStateFlow<List<Video>?> = MutableStateFlow(null)
    private val reviewsCount: MutableStateFlow<Int> = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val isFavourite: Flow<Boolean> = favoritesMoviesIdsFlow.mapLatest { favouriteIds ->
        navArgs.movieId in favouriteIds
    }
    private val externalIds: MutableStateFlow<List<ExternalId>?> = MutableStateFlow(null)
    private val additionalInfo: StateFlow<AdditionalMovieDetailsInfo> = combine(
        isFavourite, watchAtTime, watchProviders, credits, reviewsCount
    ) { isFavourite, watchAtTime, watchProviders, credits, reviewsCount ->
        AdditionalMovieDetailsInfo(
            isFavorite = isFavourite,
            watchAtTime = watchAtTime,
            watchProviders = watchProviders,
            credits = credits,
            reviewsCount = reviewsCount
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(10),
        AdditionalMovieDetailsInfo.default
    )
    private val associatedMovies: StateFlow<AssociatedMovies> = combine(
        deviceLanguage, movieCollection, otherDirectorMovies
    ) { deviceLanguage, collection, otherDirectorMovies ->
        AssociatedMovies(
            collection = collection,
            similar = getRelatedMoviesUseCase(
                movieId = navArgs.movieId,
                type = RelationType.Similar,
                deviceLanguage = deviceLanguage
            ).cachedIn(viewModelScope),
            recommendations = getRelatedMoviesUseCase(
                movieId = navArgs.movieId,
                type = RelationType.Recommended,
                deviceLanguage = deviceLanguage
            ).cachedIn(viewModelScope),
            directorMovies = otherDirectorMovies
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), AssociatedMovies.default)

    private val associatedContent: StateFlow<AssociatedContent> = combine(
        movieBackdrops, videos, externalIds
    ) { backdrops, videos, externalIds ->
        AssociatedContent(
            backdrops = backdrops,
            videos = videos,
            externalIds = externalIds
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), AssociatedContent.default)

    val uiState: StateFlow<MovieDetailsScreenUIState> = combine(
        movieDetails, additionalInfo, associatedMovies, associatedContent, error
    ) { details, additionalInfo, associatedMovies, visualContent, error ->
        MovieDetailsScreenUIState(
            startRoute = navArgs.startRoute,
            movieDetails = details,
            additionalMovieDetailsInfo = additionalInfo,
            associatedMovies = associatedMovies,
            associatedContent = visualContent,
            error = error
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        MovieDetailsScreenUIState.getDefault(navArgs.startRoute)
    )

    init {
        getMovieInfo()
    }

    private fun getMovieInfo() {
        viewModelScope.launch {
            val movieId = navArgs.movieId

            launch {
                getMovieBackdrops(movieId)
            }
            launch {
                getExternalIds(movieId)
            }
            launch {
                getMovieReview(movieId)
            }

            deviceLanguage.collectLatest { deviceLanguage ->
                launch {
                    getMovieDetails(movieId, deviceLanguage)
                }
                launch {
                    getWatchProviders(movieId, deviceLanguage)
                }
                launch {
                    getMovieCredits(movieId, deviceLanguage)
                }
                launch {
                    getMovieVideos(movieId, deviceLanguage)
                }
            }
        }

        startRefreshingWatchAtTime()
    }

    private fun startRefreshingWatchAtTime() {
        viewModelScope.launch {
            _movieDetails.collectLatest { details ->
                while (isActive) {
                    details?.runtime?.let { runtime ->
                        if (runtime > 0) {
                            runtime.minutes.toComponents { hours, minutes, _, _ ->
                                val time = Calendar.getInstance().apply {
                                    time = Date()

                                    add(Calendar.HOUR, hours.toInt())
                                    add(Calendar.MINUTE, minutes)
                                }.time

                                watchAtTime.emit(time)
                            }
                        }
                    }

                    delay(10.seconds)
                }
            }
        }
    }

    fun onLikeClick(movieDetails: MovieDetails) {
        likeMovieUseCaseImpl(movieDetails)
    }

    fun onUnlikeClick(movieDetails: MovieDetails) {
        unlikeMovieUseCase(movieDetails)
    }

    private suspend fun getMovieDetails(movieId: Int, deviceLanguage: DeviceLanguage) {
        getMovieDetailsUseCase(
            movieId = movieId,
            deviceLanguage = deviceLanguage
        ).onSuccess {
            viewModelScope.launch {
                val movieDetails = data
                _movieDetails.emit(movieDetails)

                data?.collection?.id?.let { collectionId ->
                    getMovieCollection(
                        collectionId = collectionId,
                        deviceLanguage = deviceLanguage
                    )
                }
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getMovieCredits(movieId: Int, deviceLanguage: DeviceLanguage) {
        getMovieCreditsUseCase(
            movieId = movieId,
            deviceLanguage = deviceLanguage
        ).onSuccess {
            viewModelScope.launch {
                credits.emit(data)

                val directors = data?.crew?.filter { member -> member.job == "Director" }
                val mainDirector = if (directors?.count() == 1) directors.first() else null

                if (mainDirector != null) {
                    getOtherDirectorMovies(
                        mainDirector = mainDirector,
                        deviceLanguage = deviceLanguage
                    )
                }
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getMovieBackdrops(movieId: Int) {
        getMovieBackdropsUseCase(movieId).onSuccess {
            viewModelScope.launch {
                movieBackdrops.emit(data ?: emptyList())
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getMovieReview(movieId: Int) {
        getMovieReviewsCountUseCase(movieId).onSuccess {
            viewModelScope.launch {
                reviewsCount.emit(data ?: 0)
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getMovieCollection(collectionId: Int, deviceLanguage: DeviceLanguage) {
        getMovieCollectionUseCase(
            collectionId = collectionId,
            deviceLanguage = deviceLanguage
        ).onSuccess {
            viewModelScope.launch {
                movieCollection.emit(data)
            }
        }.onFailure {
            onFailure(this)
        }.onException {
            onError(this)
        }
    }

    private suspend fun getWatchProviders(movieId: Int, deviceLanguage: DeviceLanguage) {
        getMovieWatchProvidersUseCase(
            movieId = movieId,
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

    private suspend fun getExternalIds(movieId: Int) {
        getMovieExternalIdsUseCase(
            movieId = movieId
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

    private suspend fun getMovieVideos(movieId: Int, deviceLanguage: DeviceLanguage) {
        getMoviesVideosUseCase(
            movieId = movieId,
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

    private suspend fun getOtherDirectorMovies(
        mainDirector: CrewMember,
        deviceLanguage: DeviceLanguage
    ) {
        val movies = getOtherDirectorMoviesUseCase(
            mainDirector = mainDirector,
            deviceLanguage = deviceLanguage
        ).cachedIn(viewModelScope)

        val directorMovies = DirectorMovies(
            directorName = mainDirector.name,
            movies = movies
        )

        otherDirectorMovies.emit(directorMovies)
    }
}