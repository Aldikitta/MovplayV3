package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.movie.MovieDetails

interface AddRecentlyBrowsedTvShowUseCase {
    operator fun invoke(details: MovieDetails)
}