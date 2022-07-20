package com.example.movplayv3.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExternalIds(
    val id: Int,
    @Json(name = "imdb_id")
    val imdbId: String?,

    @Json(name = "facebook_id")
    val facebookId: String?,

    @Json(name = "instagram_id")
    val instagramId: String?,

    @Json(name = "twitter_id")
    val twitterId: String?
) {
    fun toExternalIdList(type: ExternalContentType): List<ExternalId> {
        return buildList {
            facebookId?.let { id ->
                add(ExternalId.Facebook(id, type))
            }

            instagramId?.let { id ->
                add(ExternalId.Instagram(id, type))
            }

            twitterId?.let { id ->
                add(ExternalId.Twitter(id, type))
            }

            imdbId?.let { id ->
                add(ExternalId.Imdb(id, type))
            }
        }
    }
}