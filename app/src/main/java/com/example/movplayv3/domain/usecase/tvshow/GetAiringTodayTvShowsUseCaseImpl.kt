package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import androidx.paging.map
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.repository.tvshow.TvShowRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetAiringTodayTvShowsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class GetAiringTodayTvShowsUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetAiringTodayTvShowsUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(deviceLanguage: DeviceLanguage): Flow<PagingData<Presentable>> {
        return tvShowRepository.airingTodayTvShows(deviceLanguage)
            .mapLatest { data -> data.map { it } }
    }
}