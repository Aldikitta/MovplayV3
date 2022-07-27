package com.example.movplayv3.domain.usecase.interfaces.movie

interface GetDiscoverMoviesUseCase {
    operator fun invoke(
        sortInfo: SortInfo,
    )
}