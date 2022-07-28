package com.example.movplayv3.domain.usecase

import androidx.paging.PagingData
import com.example.movplayv3.data.model.MediaType
import com.example.movplayv3.data.model.Review
import com.example.movplayv3.domain.usecase.interfaces.GetMediaTypeReviewsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMediaTypeReviewsUseCaseImpl @Inject constructor() : GetMediaTypeReviewsUseCase {
    override fun invoke(mediaId: Int, type: MediaType): Flow<PagingData<Review>> {
        TODO("Not yet implemented")
    }

}