package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowVideosUseCase
import javax.inject.Inject

class GetTvShowVideosUseCaseImpl @Inject constructor() : GetTvShowVideosUseCase {
    override suspend fun invoke(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<List<Video>> {
        TODO("Not yet implemented")
    }

}