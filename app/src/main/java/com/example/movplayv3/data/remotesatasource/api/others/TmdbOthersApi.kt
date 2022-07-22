package com.example.movplayv3.data.remotesatasource.api.others

import com.example.movplayv3.data.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbOthersApi {
    @GET("configuration")
    fun getConfig(): Call<Config>

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String,
        @Query("query") query: String,
        @Query("year") year: Int?,
        @Query("include_adult") includeAdult: Boolean,
        @Query("primary_release_year") releaseYear: Int?
    ): SearchResponse

    @GET("collection/{collection_id}")
    fun getCollection(
        @Path("collection_id") collectionId: Int,
        @Query("language") isoCode: String
    ): Call<CollectionResponse>

    @GET("person/{person_id}")
    fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("language") isoCode: String
    ): Call<PersonDetails>

    @GET("person/{person_id}/combined_credits")
    fun getCombinedCredits(
        @Path("person_id") personId: Int,
        @Query("language") isoCode: String
    ): Call<CombinedCredits>

    @GET("person/{person_id}/external_ids")
    fun getPersonExternalIds(
        @Path("person_id") personId: Int,
        @Query("language") isoCode: String
    ): Call<ExternalIds>
}