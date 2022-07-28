package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import androidx.paging.map
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.tvshow.TvShowType
import com.example.movplayv3.domain.usecase.interfaces.tvshow.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class GetTvShowOfTypeUseCaseImpl @Inject constructor(
    private val getOnTheAirTvShowsUseCase: GetOnTheAirTvShowsUseCase,
    private val getTopRatedTvShowsUseCase: GetTopRatedTvShowsUseCase,
    private val getAiringTodayTvShowsUseCase: GetAiringTodayTvShowsUseCase,
    private val getTrendingTvShowsUseCase: GetTrendingTvShowsUseCase,
    private val getFavouriteTvShowsUseCase: GetFavoritesTvShowsUseCase,
    private val getRecentlyBrowsedTvShowsUseCase: GetRecentlyBrowsedTvShowsUseCase,
) : GetTvShowOfTypeUseCase {
    override fun invoke(
        type: TvShowType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Presentable>> {
        return when (type) {
            TvShowType.OnTheAir -> getOnTheAirTvShowsUseCase(deviceLanguage)
            TvShowType.TopRated -> getTopRatedTvShowsUseCase(deviceLanguage)
            TvShowType.AiringToday -> getAiringTodayTvShowsUseCase(deviceLanguage)
            TvShowType.Trending -> getTrendingTvShowsUseCase(deviceLanguage)
            TvShowType.Favorite -> getFavouriteTvShowsUseCase()
            TvShowType.RecentlyBrowsed -> getRecentlyBrowsedTvShowsUseCase()
        }.mapLatest { data -> data.map { it } }
    }
}