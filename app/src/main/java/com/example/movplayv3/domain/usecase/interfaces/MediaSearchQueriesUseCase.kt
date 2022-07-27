package com.example.movplayv3.domain.usecase.interfaces

interface MediaSearchQueriesUseCase {
    suspend operator fun invoke(query: String): List<String>
}