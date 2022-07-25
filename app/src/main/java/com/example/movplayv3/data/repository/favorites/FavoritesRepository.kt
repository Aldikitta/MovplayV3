package com.example.movplayv3.data.repository.favorites

import com.example.movplayv3.data.model.movie.MovieDetails

interface FavoritesRepository {
    fun likeMovie(movieDetails: MovieDetails)
}