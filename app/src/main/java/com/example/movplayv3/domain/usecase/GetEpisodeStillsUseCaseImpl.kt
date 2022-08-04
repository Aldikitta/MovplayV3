package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.season.SeasonRepository
import javax.inject.Inject

class GetEpisodeStillsUseCaseImpl @Inject constructor(
    private val seasonRepository: SeasonRepository
) {
    suspend operator fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): ApiResponse<List<Image>> {
        val response = seasonRepository.episodesImage(
            tvShowId = tvShowId, seasonNumber = seasonNumber, episodeNumber = episodeNumber
        ).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val stills = response.data?.stills ?: emptyList()
                ApiResponse.Success(stills)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }

}