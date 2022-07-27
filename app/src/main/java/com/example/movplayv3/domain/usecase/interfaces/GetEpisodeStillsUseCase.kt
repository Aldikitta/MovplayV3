package com.example.movplayv3.domain.usecase.interfaces

import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetEpisodeStillsUseCase {
    suspend operator fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): ApiResponse<List<Image>>
}