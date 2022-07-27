package com.example.movplayv3.data.repository.favorites

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movplayv3.data.local.db.movie.FavoritesMoviesDao
import com.example.movplayv3.data.local.db.tvshow.FavoritesTvShowsDao
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.model.movie.MovieFavorite
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.model.tvshow.TvShowFavorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor(
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val favoritesMoviesDao: FavoritesMoviesDao,
    private val favoritesTvShowsDao: FavoritesTvShowsDao
) : FavoritesRepository {
    override fun likeMovie(movieDetails: MovieDetails) {
        externalScope.launch(defaultDispatcher) {
            val favoriteMovie = movieDetails.run {
                MovieFavorite(
                    id = id,
                    posterPath = posterPath,
                    title = title,
                    originalTitle = originalTitle,
                    addedDate = Date()
                )
            }
            favoritesMoviesDao.likeMovie(favoriteMovie)
        }
    }

    override fun likeTvShow(tvShowDetails: TvShowDetails) {
        externalScope.launch(defaultDispatcher) {
            val favoriteTvShow = tvShowDetails.run {
                TvShowFavorite(
                    id = id,
                    posterPath = posterPath,
                    name = name,
                    addedDate = Date()
                )
            }
            favoritesTvShowsDao.likeTvShow(favoriteTvShow)
        }
    }

    override fun unlikeMovie(movieDetails: MovieDetails) {
        externalScope.launch {
            favoritesMoviesDao.unlikeMovie(movieDetails.id)
        }
    }

    override fun unlikeTvShows(tvShowDetails: TvShowDetails) {
        externalScope.launch {
            favoritesTvShowsDao.unlikeTvShow(tvShowDetails.id)
        }
    }

    override fun favoriteMovies(): Flow<PagingData<MovieFavorite>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        favoritesMoviesDao.getAllFavoriteMovies().asPagingSourceFactory()()
    }.flow.flowOn(defaultDispatcher)

    override fun favoriteTvShows(): Flow<PagingData<TvShowFavorite>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        favoritesTvShowsDao.getAllFavoriteTvShows().asPagingSourceFactory()()
    }.flow.flowOn(defaultDispatcher)

    override fun getFavoriteMoviesIds(): Flow<List<Int>> {
        return favoritesMoviesDao.favouriteMoviesIds().distinctUntilChanged()
    }

    override fun getFavoriteTvShowsIds(): Flow<List<Int>> {
        return favoritesTvShowsDao.favoriteTvShowIds().distinctUntilChanged()
    }

    override fun getFavoriteMoviesCount(): Flow<Int> {
        return favoritesMoviesDao.favouriteMoviesCount().distinctUntilChanged()
    }

    override fun getFavoriteTvShowsCount(): Flow<Int> {
        return favoritesTvShowsDao.favoriteTvShowCount().distinctUntilChanged()
    }
}