package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.ui.screens.discover.movies.SortInfo

interface GetDiscoverTvShowsUseCase {
    operator fun invoke(
        sortInfo: SortInfo,
        fil
    )
}