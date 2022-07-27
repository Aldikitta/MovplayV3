package com.example.movplayv3.data.repository.season

import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeasonRepositoryImpl @Inject constructor(
    private val apiTvShowHelper: TmdbTvShowsApiHelper
): SeasonRepository {
}