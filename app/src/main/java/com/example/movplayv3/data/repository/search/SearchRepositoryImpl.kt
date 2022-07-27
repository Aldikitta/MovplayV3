package com.example.movplayv3.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movplayv3.data.local.db.SearchQueryDao
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.SearchQuery
import com.example.movplayv3.data.model.SearchResult
import com.example.movplayv3.data.paging.SearchResponsePagingDataSource
import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val externalScope: CoroutineScope,
    private val apiOthersHelper: TmdbOthersApiHelper,
    private val searchQueryDao: SearchQueryDao
) : SearchRepository {
    override fun multiSearch(
        query: String,
        includeAdult: Boolean,
        year: Int?,
        releaseYear: Int?,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<SearchResult>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        SearchResponsePagingDataSource(
            apiOtherHelper = apiOthersHelper,
            includeAdult = includeAdult,
            query = query,
            year = year,
            releaseYear = releaseYear,
            language = deviceLanguage.languageCode
        )
    }.flow.flowOn(defaultDispatcher)

    override suspend fun searchQueries(query: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun addSearchQuery(searchQuery: SearchQuery) {
        TODO("Not yet implemented")
    }
}