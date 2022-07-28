package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.SearchQuery
import com.example.movplayv3.data.repository.config.ConfigRepository
import com.example.movplayv3.data.repository.search.SearchRepository
import com.example.movplayv3.domain.usecase.interfaces.MediaAddSearchQueryUseCase
import javax.inject.Inject

class MediaAddSearchQueryUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : MediaAddSearchQueryUseCase {
    override fun invoke(searchQuery: SearchQuery) {
        return searchRepository.addSearchQuery(searchQuery)
    }

}