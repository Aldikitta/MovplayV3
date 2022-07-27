package com.example.movplayv3.domain.usecase.interfaces.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.tvshow.TvShowType
import kotlinx.coroutines.flow.Flow

interface GetTvShowOfTypeUseCase {
    operator fun invoke(
        type: TvShowType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Presentable>>
}