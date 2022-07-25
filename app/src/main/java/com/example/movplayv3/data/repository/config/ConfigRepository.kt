package com.example.movplayv3.data.repository.config

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Genre
import com.example.movplayv3.data.model.ProviderSource
import com.example.movplayv3.utils.ImageUrlParser
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    fun isInitialized(): Flow<Boolean>

    fun updateLocale()

    fun getSpeechToTextAvailable(): Flow<Boolean>

    fun getCameraAvailable(): Flow<Boolean>

    fun getDeviceLanguage(): Flow<DeviceLanguage>

    fun getImageUrlParser(): Flow<ImageUrlParser?>

    fun getMoviesGenres(): Flow<List<Genre>>

    fun getTvShowGenres(): Flow<List<Genre>>

    fun getAllMoviesWatchProviders(): Flow<List<ProviderSource>>
}