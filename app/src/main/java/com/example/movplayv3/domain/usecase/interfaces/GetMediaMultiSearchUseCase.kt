package com.example.movplayv3.domain.usecase.interfaces

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface GetMediaMultiSearchUseCase {
    operator fun invoke(
        query: String,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<SearchResult>>
}