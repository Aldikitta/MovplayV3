package com.example.movplayv3.data.remotesatasource.api.others

import com.example.movplayv3.data.model.*
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbOthersApiHelperImpl @Inject constructor(
    private val tmdbOthersApi: TmdbOthersApi
) : TmdbOthersApiHelper {
    override fun getConfig(): Call<Config> {
        return tmdbOthersApi.getConfig()
    }

    override suspend fun multiSearch(
        page: Int,
        isoCode: String,
        region: String,
        query: String,
        includeAdult: Boolean,
        year: Int?,
        releaseYear: Int?
    ): SearchResponse {
        return tmdbOthersApi.multiSearch(
            page = page,
            isoCode = isoCode,
            region = region,
            query = query,
            includeAdult = includeAdult,
            year = year,
            releaseYear = releaseYear
        )
    }

    override fun getCollection(collectionId: Int, isoCode: String): Call<CollectionResponse> {
        return tmdbOthersApi.getCollection(
            collectionId, isoCode
        )
    }

    override fun getPersonDetails(personId: Int, isoCode: String): Call<PersonDetails> {
        return tmdbOthersApi.getPersonDetails(personId, isoCode)
    }

    override fun getCombinedCredits(personId: Int, isoCode: String): Call<CombinedCredits> {
        return tmdbOthersApi.getCombinedCredits(personId, isoCode)
    }

    override fun getPersonExternalIds(personId: Int, isoCode: String): Call<ExternalIds> {
        return tmdbOthersApi.getPersonExternalIds(personId, isoCode)
    }
}