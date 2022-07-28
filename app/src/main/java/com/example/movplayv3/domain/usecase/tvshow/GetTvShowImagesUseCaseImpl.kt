package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.data.model.Image
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowImagesUseCase
import javax.inject.Inject

class GetTvShowImagesUseCaseImpl @Inject constructor() : GetTvShowImagesUseCase {
    override suspend fun invoke(tvShowId: Int): ApiResponse<List<Image>> {
        TODO("Not yet implemented")
    }
}l