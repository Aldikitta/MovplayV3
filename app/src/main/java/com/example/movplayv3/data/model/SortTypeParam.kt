package com.example.movplayv3.data.model

enum class SortTypeParam(val value: String) {
    PopularityAsc("popularity.asc"),
    PopularityDesc("popularity.desc"),
    ReleaseDateAsc("release_date.asc"),
    ReleaseDateDesc("release_date.desc"),
    VoteAverageAsc("vote_average.asc"),
    VoteAverageDesc("vote_average.desc"),
    OriginalTitleAsc("original_title.asc"),
    OriginalTitleDesc("original_title.desc"),
    VoteCountAsc("vote_count.asc"),
    VoteCountDesc("vote_count.desc");

    override fun toString() = value

}