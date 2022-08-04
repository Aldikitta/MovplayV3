package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.SearchQuery
import com.example.movplayv3.data.repository.search.SearchRepository
import javax.inject.Inject

class MediaAddSearchQueryUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(searchQuery: SearchQuery) {
        return searchRepository.addSearchQuery(searchQuery)
    }

}