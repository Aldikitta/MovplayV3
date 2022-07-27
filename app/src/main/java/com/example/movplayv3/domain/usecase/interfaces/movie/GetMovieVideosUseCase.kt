package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetMovieVideosUseCase {
    suspend operator fun invoke(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<List<Video>>
}