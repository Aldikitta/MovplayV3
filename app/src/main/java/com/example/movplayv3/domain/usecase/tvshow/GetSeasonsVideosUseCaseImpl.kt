package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetSeasonsVideosUseCase
import javax.inject.Inject


class GetSeasonsVideosUseCaseImpl @Inject constructor() : GetSeasonsVideosUseCase {
    override suspend fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<List<Video>> {
        TODO("Not yet implemented")
    }

}