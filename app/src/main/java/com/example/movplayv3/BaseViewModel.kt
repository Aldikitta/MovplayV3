package com.example.movplayv3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

open class BaseViewModel : ViewModel() {
    private val _error: MutableSharedFlow<String?> = MutableSharedFlow(replay = 0)
    val error: StateFlow<String?> = _error.asSharedFlow().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(10), null
    )
}