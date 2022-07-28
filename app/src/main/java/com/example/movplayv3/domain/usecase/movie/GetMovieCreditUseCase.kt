package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.Credits
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetMovieCreditUseCase {
    suspend operator fun invoke(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<Credits>
}