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
        return tmdbMoviesApi.getPopularMovies(page, isoCode, region)
    }

    override suspend fun getUpcomingMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        return tmdbMoviesApi.getUpcomingMovies(page, isoCode, region)
    }

    override suspend fun getTopRatedMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        return tmdbMoviesApi.getTopRatedMovies(page, isoCode, region)
    }

    override suspend fun getNowPlayingMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
        return tmdbMoviesApi.getNowPlayingMovies(page, isoCode, region)
    }

    override fun getMovieDetails(movieId: Int, isoCode: String): Call<MovieDetails> {
        return tmdbMoviesApi.getMovieDetails(movieId, isoCode)
    }

    override fun getMovieCredits(movieId: Int, isoCode: String): Call<Credits> {
//        return tmdbMoviesApi.getMovieCredits(movieId, isoCode)
    }

    override suspend fun getSimilarMovies(
        movieId: Int,
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
//        return tmdbMoviesApi.getSimilarMovies(movieId, page, isoCode, region)
    }

    override suspend fun getMoviesRecommendations(
        movieId: Int,
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
//        return tmdbMoviesApi.getMoviesRecommendations(movieId, page, isoCode, region)
    }

    override suspend fun getTrendingMovies(
        page: Int,
        isoCode: String,
        region: String
    ): MoviesResponse {
//        return tmdbMoviesApi.getTrendingMovies(page, isoCode, region)
    }

    override fun getMovieImages(movieId: Int): Call<ImagesResponse> {
//        return tmdbMoviesApi.getMovieImages(movieId)
    }

    override suspend fun getMovieReviews(movieId: Int, page: Int): ReviewsResponse {
//        return tmdbMoviesApi.getMovieReviews(movieId, page)
    }

    override fun getMovieReview(movieId: Int): Call<ReviewsResponse> {
//        return tmdbMoviesApi.getMovieReview(movieId)
    }

    override fun getMoviesGenres(isoCode: String): Call<GenresResponse> {
//        return tmdbMoviesApi.getMovieGenres(isoCode)
    }

    override fun getMovieWatchProviders(movieId: Int): Call<WatchProvidersResponse> {
//        return tmdbMoviesApi.getMovieWatchProviders(movieId)
    }

    override fun getMovieExternalIds(movieId: Int): Call<ExternalIds> {
//        return tmdbMoviesApi.getMovieExternalIds(movieId)
    }

    override fun getAllMoviesWatchProviders(
        isoCode: String,
        region: String
    ): Call<AllWatchProvidersResponse> {
//        return tmdbMoviesApi.getAllMoviesWatchProviders(isoCode, region)
    }

    override fun getMovieVideos(movieId: Int, isoCode: String): Call<VideosResponse> {
//        return tmdbMoviesApi.getMovieVideos(movieId, isoCode)
    }

    override suspend fun getOtherMoviesOfDirector(
        page: Int,
        isoCode: String,
        region: String,
        directorId: Int
    ): MoviesResponse {
//        return tmdbMoviesApi.getOtherMoviesOfDirector(page, isoCode, region, directorId)
    }
}