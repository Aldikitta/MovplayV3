package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import androidx.paging.map
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.movie.MovieType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetMoviesOfTypeUseCaseImpl @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCaseImpl,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCaseImpl,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCaseImpl,
    private val getFavoritesMoviesUseCaseImpl: GetFavoritesMoviesUseCaseImpl,
    private val getRecentlyBrowsedMoviesUseCase: GetRecentlyBrowsedMoviesUseCaseImpl,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCaseImpl
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        type: MovieType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Presentable>> {
        return when (type) {
            MovieType.NowPlaying -> getNowPlayingMoviesUseCase(deviceLanguage, false)
            MovieType.TopRated -> getTopRatedMoviesUseCase(deviceLanguage)
            MovieType.Upcoming -> getUpcomingMoviesUseCase(deviceLanguage)
            MovieType.Favorite -> getFavoritesMoviesUseCaseImpl()
            MovieType.RecentlyBrowsed -> getRecentlyBrowsedMoviesUseCase()
            MovieType.Trending -> getTrendingMoviesUseCase(deviceLanguage)
        }.mapLatest { data -> data.map { it } }
    }
}