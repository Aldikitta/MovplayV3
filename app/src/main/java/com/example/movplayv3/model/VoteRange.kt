package com.example.movplayv3.model

data class VoteRange(
    val default: ClosedFloatingPointRange<Float> = 0f..10f,
    val current: ClosedFloatingPointRange<Float> = default
)
