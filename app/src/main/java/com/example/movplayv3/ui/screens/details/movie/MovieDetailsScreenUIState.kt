package com.example.movplayv3.ui.screens.details.movie

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.data.model.movie.MovieCollection
import com.example.movplayv3.data.model.movie.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.util.*

@Stable
data class MovieDetailsScreenUIState(
    val startRoute: String,
    val movieDetails: MovieDetails?,
    val additionalMovieDetailsInfo: AdditionalMovieDetailsInfo,
    val associatedMovies: AssociatedMovies,
    val associatedContent: AssociatedContent,
    val error: String?
) {
    companion object {
        fun getDefault(startRoute: String): MovieDetailsScreenUIState {
            return MovieDetailsScreenUIState(
                startRoute = startRoute,
                movieDetails = null,
                additionalMovieDetailsInfo = AdditionalMovieDetailsInfo.default,
                associatedMovies = AssociatedMovies.default,
                associatedContent = AssociatedContent.default,
                error = null
            )
        }
    }
}

@Stable
data class AssociatedMovies(
    val collection: MovieCollection?,
    val similar: Flow<PagingData<Movie>>,
    val recommendation: Flow<PagingData<Movie>>,
    val directorMovies: DirectorMovies
) {
    companion object {
        val default: AssociatedMovies = AssociatedMovies(
            collection = null,
            similar = emptyFlow(),
            recommendation = emptyFlow(),
            directorMovies = DirectorMovies.default
        )
    }
}

@Stable
data class DirectorMovies(
    val directorName: String,
    val movies: Flow<PagingData<Movie>>
) {
    companion object {
        val default: DirectorMovies = DirectorMovies(
            directorName = "",
            movies = emptyFlow()
        )
    }
}

@Stable
data class AdditionalMovieDetailsInfo(
    val isFavorite: Boolean,
    val watchAttIME: Date?,
    val watchProviders: WatchProviders?,
    val credits: Credits?,
    val reviewsCount: Int
) {
    companion object {
        val default: AdditionalMovieDetailsInfo = AdditionalMovieDetailsInfo(
            isFavorite = false,
            watchAttIME = null,
            watchProviders = null,
            credits = null,
            reviewsCount = 0
        )
    }
}

@Stable
data class AssociatedContent(
    val backdrops: List<Image>,
    val videos: List<Video>?,
    val externalIds: List<ExternalId>?
) {
    companion object {
        val default: AssociatedContent = AssociatedContent(
            backdrops = emptyList(),
            videos = null,
            externalIds = null
        )
    }
}