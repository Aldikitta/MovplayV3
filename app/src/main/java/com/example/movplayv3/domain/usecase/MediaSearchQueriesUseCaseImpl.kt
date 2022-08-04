package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.repository.search.SearchRepository
import javax.inject.Inject

class MediaSearchQueriesUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): List<String> {
        return searchRepository.searchQueries(query)
    }
}