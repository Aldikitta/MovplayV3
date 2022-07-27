package com.example.movplayv3.domain.usecase.interfaces

import com.example.movplayv3.data.model.SearchQuery

interface MediaAddSearchQueryUseCase {
    operator fun invoke(searchQuery: SearchQuery)
}