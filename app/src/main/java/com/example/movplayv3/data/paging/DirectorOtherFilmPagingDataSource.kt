package com.example.movplayv3.data.paging

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper

class DirectorOtherFilmPagingDataSource(
    private val apiOtherHelper: TmdbOthersApiHelper,
    private val language: String = DeviceLanguage.default.languageCode,
    private val region: String = DeviceLanguage.default.region,
    private val directorId: Int
    ) {

}