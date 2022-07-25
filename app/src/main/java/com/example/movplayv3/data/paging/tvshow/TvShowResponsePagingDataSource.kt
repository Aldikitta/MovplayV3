package com.example.movplayv3.data.paging.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.tvshow.TvShowsResponse

class TvShowResponsePagingDataSource(
    private val language: String = DeviceLanguage.default.languageCode,
    private val region: String = DeviceLanguage.default.region,
    private inline val apiHelperMethod: suspend (Int, String, String) -> TvShowsResponse
) {
}