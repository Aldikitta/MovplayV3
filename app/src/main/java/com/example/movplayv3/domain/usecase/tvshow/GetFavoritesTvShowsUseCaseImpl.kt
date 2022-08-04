package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.tvshow.TvShowFavorite
import com.example.movplayv3.data.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFavoritesTvShowsUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<PagingData<TvShowFavorite>> {
        return favoritesRepository.favoriteTvShows()
    }
}