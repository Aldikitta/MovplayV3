package com.example.movplayv3.domain.usecase

import androidx.paging.PagingData
import com.example.movplayv3.data.model.FavoriteType
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.domain.usecase.interfaces.GetFavoritesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFavoritesUseCaseImpl @Inject constructor() : GetFavoritesUseCase {
    override fun invoke(type: FavoriteType): Flow<PagingData<Presentable>> {
        TODO("Not yet implemented")
    }
}