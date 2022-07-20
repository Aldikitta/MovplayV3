package com.example.movplayv3.data.model

data class GenresParam(private val genres: List<Genre>) {
    override fun toString(): String {
        return genres.distinct().map { genre ->
            genre.id
        }.joinToString(separator = "|")
    }
}
