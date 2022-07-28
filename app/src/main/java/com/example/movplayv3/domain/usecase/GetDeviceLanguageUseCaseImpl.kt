package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.domain.usecase.interfaces.GetDeviceLanguageUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetDeviceLanguageUseCaseImpl @Inject constructor() : GetDeviceLanguageUseCase {
    override fun invoke(): Flow<DeviceLanguage> {
        TODO("Not yet implemented")
    }
}