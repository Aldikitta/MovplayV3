package com.example.movplayv3.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CombinedCredits(
    val cast: List<CombinedCreditsCast>,
    val crew: List<CombinedCreditsCrew>
)