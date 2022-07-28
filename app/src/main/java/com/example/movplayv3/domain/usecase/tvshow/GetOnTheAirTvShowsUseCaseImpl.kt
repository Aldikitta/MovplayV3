package com.example.movplayv3.domain.usecase.interfaces.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DetailPresentable
import com.example.movplayv3.data.model.DeviceLanguage
import kotlinx.coroutines.flow.Flow

interface GetOnTheAirTvShowsUseCaseImpl {
    operator fun invoke(
        deviceLanguage: DeviceLanguage,
        filterId: Boolean = false
    ): Flow<PagingData<DetailPresentable>>
}