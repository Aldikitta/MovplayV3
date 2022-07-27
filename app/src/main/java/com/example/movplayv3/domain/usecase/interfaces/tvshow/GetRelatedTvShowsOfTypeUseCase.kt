package com.example.movplayv3.domain.usecase.interfaces.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.RelationType
import com.example.movplayv3.data.model.tvshow.TvShow
import kotlinx.coroutines.flow.Flow

interface GetRelatedTvShowsOfTypeUseCase {
    operator fun invoke(
        tvShowId: Int,
        type: RelationType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<TvShow>>
}