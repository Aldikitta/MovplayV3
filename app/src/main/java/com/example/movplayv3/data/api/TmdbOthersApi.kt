package com.example.movplayv3.data.api

import com.example.movplayv3.data.model.CollectionResponse
import com.example.movplayv3.data.model.Config
import com.example.movplayv3.data.model.PersonDetails
import com.example.movplayv3.data.model.SearchResponse
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
}