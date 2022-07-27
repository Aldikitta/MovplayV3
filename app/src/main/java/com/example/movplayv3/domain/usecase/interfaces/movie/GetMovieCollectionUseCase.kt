package com.example.movplayv3.domain.usecase.interfaces.movie

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.MovieCollection
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetMovieCollectionUseCase {
    suspend operator fun invoke(
        collectionId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<MovieCollection?>
}