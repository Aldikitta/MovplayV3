package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetTvShowImagesUseCase {
    suspend operator fun invoke(tvShowId: Int): ApiResponse<List<Image>>
}