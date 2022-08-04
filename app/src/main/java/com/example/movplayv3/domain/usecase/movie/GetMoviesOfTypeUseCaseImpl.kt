package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import androidx.paging.map
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.movie.MovieType
import com.example.movplayv3.domain.usecase.interfaces.movie.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetMoviesOfTypeUseCaseImpl @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getFavoritesMoviesUseCaseImpl: GetFavoritesMoviesUseCase,
    private val getRecentlyBrowsedMoviesUseCase: GetRecentlyBrowsedMoviesUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCaseImpl
) : GetMoviesOfTypeUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(
        type: MovieType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Presentable>> {
        return when (type) {
            MovieType.NowPlaying -> getNowPlayingMoviesUseCase(deviceLanguage)
            MovieType.TopRated -> getTopRatedMoviesUseCase(deviceLanguage)
            MovieType.Upcoming -> getUpcomingMoviesUseCase(deviceLanguage)
            MovieType.Favourite -> getFavoritesMoviesUseCaseImpl()
            MovieType.RecentlyBrowsed -> getRecentlyBrowsedMoviesUseCase()
            MovieType.Trending -> getTrendingMoviesUseCase(deviceLanguage)
        }.mapLatest { data -> data.map { it } }
    }
}