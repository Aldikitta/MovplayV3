package com.example.movplayv3.domain.usecase

import javax.inject.Inject

class MediaAddSearchQueryUseCaseImpl @Inject constructor() : MediaSearchQueriesUseCaseImpl {
    override suspend fun invoke(query: String): List<String> {
        TODO("Not yet implemented")
    }
}