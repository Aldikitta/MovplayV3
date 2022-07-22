package com.example.movplayv3.data.api

import com.example.movplayv3.data.model.Config
import retrofit2.Call
import retrofit2.http.GET

interface TmdbTvShowsApi {
    @GET("configuration")
    fun getConfig(): Call<Config>
}