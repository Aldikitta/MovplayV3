package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.GetEpisodeStillsUseCase
import javax.inject.Inject

class GetEpisodeStillsUseCaseImpl @Inject constructor() : GetEpisodeStillsUseCase {
    override suspend fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): ApiResponse<List<Image>> {
        TODO("Not yet implemented")
    }

}