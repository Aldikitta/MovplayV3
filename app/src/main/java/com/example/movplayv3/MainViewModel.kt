package com.example.movplayv3

import android.app.usage.NetworkStatsManager
import androidx.lifecycle.viewModelScope
import com.example.movplayv3.data.model.SnackBarEvent
import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.utils.NetworkStatus
import com.example.movplayv3.utils.NetworkStatusTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkStatusTracker: NetworkStatusTracker,
    private val configRepository: ConfigRepository
) : BaseViewModel() {
    private val connectionStatus = networkStatusTracker.connectionStatus

    val networkSnackBarEvent: StateFlow<SnackBarEvent?> = connectionStatus.mapLatest { status ->
        when (status) {
            NetworkStatus.Connected -> SnackBarEvent.NetworkConnected
            NetworkStatus.Disconnected -> SnackBarEvent.NetworkDisconnected
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val sameBottomBarRouteChannel: Channel<String> = Channel()
    val sameBottomRoute: Flow<String> =
        sameBottomBarRouteChannel.receiveAsFlow().flowOn(Dispatchers.Main.immediate)


}