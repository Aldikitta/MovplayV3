package com.example.movplayv3.ui.screens.search

import androidx.compose.runtime.Stable

@Stable
data class SearchScreenUIState(
    val searchOptionsState: SearchOptionsState,
    val query: String?,
    val suggestions: List<String>,
    val searchState: SearchState,
    val resultState: ResultState,
    val queryLoading: Boolean
) {
}

@Stable
data class SearchOptionsState(
    val voiceSearchAvailable: Boolean,
    val cameraSearchAvailable: Boolean
){
    companion object{
        val default: SearchOptionsState = SearchOptionsState(
            voiceSearchAvailable = false,
            cameraSearchAvailable = false
        )
    }
}