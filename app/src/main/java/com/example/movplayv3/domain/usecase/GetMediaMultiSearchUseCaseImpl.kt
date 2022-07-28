package com.example.movplayv3.domain.usecase

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.SearchResult
import com.example.movplayv3.data.repository.search.SearchRepository
import com.example.movplayv3.domain.usecase.interfaces.GetMediaMultiSearchUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMediaMultiSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetMediaMultiSearchUseCase {
    override fun invoke(
        query: String,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<SearchResult>> {
        return searchRepository.multiSearch(query = query, deviceLanguage = deviceLanguage)
    }

}