package com.example.movplayv3.domain.usecase

import androidx.paging.PagingData
import com.example.movplayv3.data.model.MediaType
import com.example.movplayv3.data.model.Review
import kotlinx.coroutines.flow.Flow

interface GetMediaTypeReviewsUseCase {
    operator fun invoke(
        mediaId: Int,
        type: MediaType
    ): Flow<PagingData<Review>>
}