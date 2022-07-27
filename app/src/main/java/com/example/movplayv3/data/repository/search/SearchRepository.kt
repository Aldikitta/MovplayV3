package com.example.movplayv3.data.repository.search

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun multiSearch(
        query: String,
        includeAdult: Boolean = false,
        year: Int? = null,
        releaseYear: Int? = null,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<SearchResult>>

    suspend fun searchQueries(query: String): List<String>

    fun add
}