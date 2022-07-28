package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.DeviceLanguage
import kotlinx.coroutines.flow.Flow

interface GetDeviceLanguageUseCaseImpl {
    operator fun invoke(): Flow<DeviceLanguage>
}