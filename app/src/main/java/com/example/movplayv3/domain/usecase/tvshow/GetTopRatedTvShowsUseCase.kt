package com.example.movplayv3.domain.usecase.interfaces.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import kotlinx.coroutines.flow.Flow

interface GetTopRatedTvShowsUseCase {
    operator fun invoke(deviceLanguage: DeviceLanguage): Flow<PagingData<Presentable>>
}