package com.example.movplayv3.data.repository.config

import com.example.movplayv3.data.model.DeviceLanguage
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    fun isInitialized(): Flow<Boolean>

    fun updateLocale()

    fun getSpeechToTextAvailable(): Flow<Boolean>

    fun getCameraAvailable(): Flow<Boolean>

    fun deviceLanguage(): Flow<DeviceLanguage>
}