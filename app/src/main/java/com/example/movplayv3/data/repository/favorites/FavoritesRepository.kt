package com.example.movplayv3.data.repository.favorites

import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.model.tvshow.TvShowDetails

interface FavoritesRepository {
    fun likeMovie(movieDetails: MovieDetails)

    fun likeTvShow(tvShowDetails: TvShowDetails)
}