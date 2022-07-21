package com.example.movplayv3.data.model

data class ShareDetails(
    val title: String,
    val imdbId: ExternalId.Imdb
) {
    fun asMessage(): String {
        val contentLink = when (imdbId.type) {
            ExternalContentType.Movie, ExternalContentType.Tv -> "title"
            else -> "name"
        }
        val url = "https://www.imdb.com/$contentLink/${imdbId.id}"

        return "$title\n\n$url"
    }
}
