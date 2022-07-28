package com.example.movplayv3.domain.usecase

import com.example.movplayv3.data.model.SearchQuery

interface MediaAddSearchQueryUseCaseImpl {
    operator fun invoke(searchQuery: SearchQuery)
}