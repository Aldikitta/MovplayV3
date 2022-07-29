package com.example.movplayv3

import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.utils.NetworkStatusTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkStatusTracker: NetworkStatusTracker,
    private val configRepository: ConfigRepository
) : BaseViewModel() {
}