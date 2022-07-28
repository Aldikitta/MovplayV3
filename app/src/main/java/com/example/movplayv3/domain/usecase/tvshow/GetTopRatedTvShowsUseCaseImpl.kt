package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTopRatedTvShowsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTopRatedTvShowsUseCaseImpl @Inject constructor() : GetTopRatedTvShowsUseCase {
    override fun invoke(deviceLanguage: DeviceLanguage): Flow<PagingData<Presentable>> {
        TODO("Not yet implemented")
    }
}