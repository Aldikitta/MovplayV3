package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.model.tvshow.TvShowType
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTvShowOfTypeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTvShowOfTypeUseCaseImpl @Inject constructor() : GetTvShowOfTypeUseCase {
    override fun invoke(
        type: TvShowType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Presentable>> {
        TODO("Not yet implemented")
    }

}