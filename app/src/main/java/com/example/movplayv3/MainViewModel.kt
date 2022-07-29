package com.example.movplayv3

import com.example.movplayv3.data.model.SnackBarEvent
import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.utils.NetworkStatusTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkStatusTracker: NetworkStatusTracker,
    private val configRepository: ConfigRepository
) : BaseViewModel() {
    private val connectionStatus = networkStatusTracker.connectionStatus

    val networkSnackbarEvent: StateFlow<SnackBarEvent?> = connectionStatus.mapLatest { status ->
        when (status){

        }
    }
}