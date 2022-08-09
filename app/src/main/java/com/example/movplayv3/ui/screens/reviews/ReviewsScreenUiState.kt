package com.example.movplayv3.ui.screens.reviews

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.movplayv3.data.model.Review
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class ReviewsScreenUiState(
    val startRoute: String,
    val reviews: Flow<PagingData<Review>>
) {
    companion object {
        val default: ReviewsScreenUiState = ReviewsScreenUiState(
            startRoute = MovieScreenDestination.route,
            reviews = emptyFlow()
        )
    }
}