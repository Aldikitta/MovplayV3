package com.example.movplayv3.data.remotesatasource.api.movie

import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.model.movie.MoviesResponse
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbMoviesApiHelperImpl @Inject constructor(
    private val tmdbMoviesApi: TmdbMoviesApi
) : TmdbMoviesApiHelper {
    override fun getConfig(): Call<Config> {
        return tmdbMoviesApi.getConfig()
    }

    override suspend fun discoverMovies(
        page: Int,
        isoCode: String,
        region: String,
        sortTypeParam: SortTypeParam,
        genresParam: GenresParam,
        watchProvidersParam: WatchProvidersParam,
        voteRange: ClosedFloatingPointRange<Float>,
        fromReleaseDate: DateParam?,
        toReleaseDate: DateParam?
    ): MoviesResponse {
        return tmdbMoviesApi.discoverMovies(
            page,
            isoCode,
            region,
            sortTypeParam,
            genresParam,
            watchProvidersParam,
            voteAverageMin = voteRange.start,
            voteAverageMax = voteRange.endInclusive,
            fromReleaseDate = fromReleaseDate,
            toReleaseDate = toReleaseDate
        )
    }

    override suspend fun getPopularMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcomingMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlayingMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        TODO("Not yet implemented")
    }

    override fun getMovieDetails(movieId: Int, isoCode: String): Call<MovieDetails> {
        TODO("Not yet implemented")
    }

    override fun getMovieCredits(movieId: Int, isoCode: String): Call<Credits> {
        TODO("Not yet implemented")
    }

    override suspend fun getSimilarMovies(
        movieId: Int,
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getMoviesRecommendations(
        movieId: Int,
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        TODO("Not yet implemented")
    }

    override fun getMovieImages(movieId: Int): Call<ImagesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieReviews(movieId: Int, page: Int): ReviewsResponse {
        TODO("Not yet implemented")
    }

    override fun getMovieReview(movieId: Int): Call<ReviewsResponse> {
        TODO("Not yet implemented")
    }

    override fun getMoviesGenres(isoCode: String): Call<GenresResponse> {
        TODO("Not yet implemented")
    }

    override fun getMovieWatchProviders(movieId: Int): Call<WatchProvidersResponse> {
        TODO("Not yet implemented")
    }

    override fun getMovieExternalIds(movieId: Int): Call<ExternalIds> {
        TODO("Not yet implemented")
    }

    override fun getAllMoviesWatchProviders(
        isoCode: String,
        region: String
    ): Call<AllWatchProvidersResponse> {
        TODO("Not yet implemented")
    }

    override fun getMovieVideos(movieId: Int, isoCode: String): Call<VideosResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getOtherMoviesOfDirector(
        page: Int,
        isoCode: String,
        region: String,
        directorId: Int
    ): MoviesResponse {
        TODO("Not yet implemented")
    }
}