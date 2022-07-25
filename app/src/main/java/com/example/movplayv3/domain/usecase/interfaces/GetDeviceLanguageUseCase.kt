package com.example.movplayv3.domain.usecase.interfaces

import com.example.movplayv3.data.model.DeviceLanguage
import kotlinx.coroutines.flow.Flow

interface GetDeviceLanguageUseCase {
    operator fun invoke(): Flow<DeviceLanguage>
}