package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.tvshow.TvShowRepository
import javax.inject.Inject


class GetTvShowDetailsUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    suspend operator fun invoke(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<TvShowDetails> {
        return tvShowRepository.getTvShowDetails(
            tvShowId = tvShowId,
            deviceLanguage = deviceLanguage
        ).awaitApiResponse()
    }

}