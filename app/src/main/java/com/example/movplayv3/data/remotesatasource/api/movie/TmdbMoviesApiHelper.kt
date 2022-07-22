package com.example.movplayv3.data.remotesatasource.api.movie

import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.model.movie.MoviesResponse
import retrofit2.Call

interface TmdbMoviesApiHelper {
    fun getConfig(): Call<Config>

    suspend fun discoverMovies(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region,
        sortTypeParam: SortTypeParam = SortTypeParam.PopularityDesc,
        genresParam: GenresParam = GenresParam(genres = emptyList()),
        watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
        voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
        fromReleaseDate: DateParam? = null,
        toReleaseDate: DateParam? = null
    ): MoviesResponse

    suspend fun getPopularMovies(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region,
    ): MoviesResponse

    suspend fun getUpcomingMovies(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region,
    ): MoviesResponse

    suspend fun getTopRatedMovies(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region,
    ): MoviesResponse

    suspend fun getNowPlayingMovies(
        page: Int,
        isoCode: String = DeviceLanguage.default.languageCode,
        region: String = DeviceLanguage.default.region
    ): MoviesResponse

    fun getMovieDetails(
        movieId: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<MovieDetails>

    fun getMovieCredits(
        movieId: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<Credits>
}