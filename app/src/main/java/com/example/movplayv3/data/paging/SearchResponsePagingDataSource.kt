package com.example.movplayv3.data.paging

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper

class SearchResponsePagingDataSource(
    private val apiOtherHelper: TmdbOthersApiHelper,
    private val query: String,
    private val includeAdult: Boolean,
    private val year: Int? = null,
    private val releaseYear: Int? = null,
    private val language: String = DeviceLanguage.default.languageCode,
    private val region: String = DeviceLanguage.default.region
) {
}