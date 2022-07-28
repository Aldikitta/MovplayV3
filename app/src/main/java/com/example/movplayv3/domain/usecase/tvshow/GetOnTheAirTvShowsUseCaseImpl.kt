package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DetailPresentable
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetOnTheAirTvShowsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetOnTheAirTvShowsUseCaseImpl @Inject constructor() : GetOnTheAirTvShowsUseCase {
    override fun invoke(
        deviceLanguage: DeviceLanguage,
        filterId: Boolean
    ): Flow<PagingData<DetailPresentable>> {
        TODO("Not yet implemented")
    }

}