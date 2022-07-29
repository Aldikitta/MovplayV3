package com.example.movplayv3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movplayv3.data.remote.api.ApiResponse
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val _error: MutableSharedFlow<String?> = MutableSharedFlow(replay = 0)
    val error: StateFlow<String?> = _error.asSharedFlow().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(10), null
    )

    protected fun <T> onError(response: ApiResponse.Exception<T>) {
        FirebaseCrashlytics.getInstance().recordException(response.exception)

        viewModelScope.launch {
            _error.emit(response.exception.localizedMessage)
        }
    }

    protected fun <T> onFailure(response: ApiResponse.Failure<T>) {
        viewModelScope.launch {
            _error.emit(response.apiError.statusMessage)
        }
    }
}