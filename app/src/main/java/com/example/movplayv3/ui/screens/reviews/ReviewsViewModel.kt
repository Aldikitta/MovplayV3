package com.example.movplayv3.ui.screens.reviews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.domain.usecase.GetMediaTypeReviewsUseCaseImpl
import com.example.movplayv3.ui.screens.destinations.ReviewsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val getMediaTypeReviewsUseCase: GetMediaTypeReviewsUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val navArgs: ReviewsScreenNavArgs = ReviewsScreenDestination.argsFrom(savedStateHandle)

    val uiState: StateFlow<ReviewsScreenUiState> = MutableStateFlow(
        ReviewsScreenUiState(
            startRoute = navArgs.startRoute,
            reviews = getMediaTypeReviewsUseCase(
                mediaId = navArgs.mediaId,
                type = navArgs.type
            ).cachedIn(viewModelScope)
        )
    ).stateIn(viewModelScope, SharingStarted.Eagerly, ReviewsScreenUiState.default)
}