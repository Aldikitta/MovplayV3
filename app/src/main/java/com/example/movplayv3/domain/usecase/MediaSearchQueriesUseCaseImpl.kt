package com.example.movplayv3.domain.usecase

interface MediaSearchQueriesUseCaseImpl {
    suspend operator fun invoke(query: String): List<String>
}