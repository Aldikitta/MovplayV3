package com.example.movplayv3.ui.screens.seasons

import androidx.compose.runtime.Stable
import com.example.movplayv3.data.model.AggregatedCredits
import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.model.SeasonDetails
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.ui.screens.destinations.TvShowScreenDestination

@Stable
data class SeasonDetailsScreenUiState(
    val startRoute: String,
    val seasonDetails: SeasonDetails?,
    val aggregatedCredits: AggregatedCredits?,
    val videos: List<Video>?,
    val episodeCount: Int?,
    val episodeStills: Map<Int, List<Image>>,
    val error: String?
) {
    companion object {
        val default: SeasonDetailsScreenUiState = SeasonDetailsScreenUiState(
            startRoute = TvShowScreenDestination.route,
            seasonDetails = null,
            aggregatedCredits = null,
            videos = null,
            episodeCount = null,
            episodeStills = emptyMap(),
            error = null
        )
    }
}