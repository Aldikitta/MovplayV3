package com.example.movplayv3.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.movplayv3.data.model.FavoriteType
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import com.example.movplayv3.domain.usecase.interfaces.GetFavoritesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class GetFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : GetFavoritesUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun invoke(type: FavoriteType): Flow<PagingData<Presentable>> {
        val favorites = when (type) {
            FavoriteType.Movie -> favoritesRepository.favoriteMovies()
            FavoriteType.TvShow -> favoritesRepository.favoriteTvShows()
        }.mapLatest { data -> data.map { it } }

        return favorites
    }
}