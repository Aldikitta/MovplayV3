package com.example.movplayv3.domain.usecase

import androidx.paging.PagingData
import com.example.movplayv3.data.model.FavoriteType
import com.example.movplayv3.data.model.Presentable
import kotlinx.coroutines.flow.Flow

interface GetFavoritesUseCaseImpl {
    operator fun invoke(type: FavoriteType): Flow<PagingData<Presentable>>
}