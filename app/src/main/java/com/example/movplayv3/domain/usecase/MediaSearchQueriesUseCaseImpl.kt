package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.repository.search.SearchRepository
import com.example.movplayv3.domain.usecase.interfaces.MediaSearchQueriesUseCase
import javax.inject.Inject

class MediaSearchQueriesUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : MediaSearchQueriesUseCase{
    override suspend fun invoke(query: String): List<String> {
        return searchRepository.searchQueries(query)
    }
}