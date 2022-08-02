package com.example.movplayv3.ui.screens.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.SearchResult
import com.example.movplayv3.domain.usecase.interfaces.*
import com.example.movplayv3.domain.usecase.interfaces.movie.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

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
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()
    private val queryDelay = 500.milliseconds
    private val minQueryLength = 3

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val popularMovies: Flow<PagingData<Presentable>> =
        deviceLanguage.mapLatest { deviceLanguage ->
            getPopularMoviesUseCase(deviceLanguage)
        }.flattenMerge().cachedIn(viewModelScope)

    private val voiceSearchAvailable: Flow<Boolean> = getSpeechToTextAvailableUseCase()
    private val cameraSearchAvailable: Flow<Boolean> = getCameraAvailableUseCase()

    private val queryState: MutableStateFlow<QueryState> = MutableStateFlow(QueryState.default)
    private val suggestions: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val searchState: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState.EmptyQuery)
    private val resultState: MutableStateFlow<ResultState> =
        MutableStateFlow(ResultState.Default(popularMovies))
    private val queryLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val searchOptionState: StateFlow<SearchOptionsState> = combine(
        voiceSearchAvailable, cameraSearchAvailable
    ) { voiceSearchAvailable, cameraSearchAvailable ->
        SearchOptionsState(
            voiceSearchAvailable = voiceSearchAvailable,
            cameraSearchAvailable = cameraSearchAvailable
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, SearchOptionsState.default)

    private var queryJob: Job? = null

    val uiState: StateFlow<SearchScreenUIState> = combine(
        searchOptionState, queryState, suggestions, searchState, resultState
    ) { searchOptionsState, queryState, suggestions, searchState, resultState ->
        SearchScreenUIState(
            searchOptionsState = searchOptionsState,
            query = queryState.query,
            suggestions = suggestions,
            searchState = searchState,
            resultState = resultState,
            queryLoading = queryState.loading
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, SearchScreenUIState.default)
}

sealed class SearchState {
    object EmptyQuery : SearchState()
    object InsufficientQuery : SearchState()
    object ValidQuery : SearchState()
}

sealed class ResultState {
    data class Default(val popular: Flow<PagingData<Presentable>> = emptyFlow()) : ResultState()
    data class Search(val result: Flow<PagingData<SearchResult>>) : ResultState()
}

data class QueryState(
    val query: String?,
    val loading: Boolean
) {
    companion object {
        val default: QueryState = QueryState(
            query = null,
            loading = false
        )
    }
}