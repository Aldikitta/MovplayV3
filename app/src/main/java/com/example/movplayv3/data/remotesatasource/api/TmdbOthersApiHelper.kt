package com.example.movplayv3.data.remotesatasource.api

import com.example.movplayv3.data.model.*
import retrofit2.Call

interface TmdbOthersApiHelper {
    fun getConfig(): Call<Config>

    suspend fun multiSearch(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region,
        query: String,
        includeAdult: Boolean = false,
        year: Int? = null,
        releaseYear: Int? = null
    ): SearchResponse

    fun getCollection(
        collectionId: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<CollectionResponse>

    fun getPersonDetails(
        personId: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<PersonDetails>

    fun getCombinedCredits(
        personId: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<CombinedCredits>
}