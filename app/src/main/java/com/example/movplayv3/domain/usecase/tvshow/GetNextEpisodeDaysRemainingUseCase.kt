package com.example.movplayv3.domain.usecase.interfaces.tvshow

import java.util.*

interface GetNextEpisodeDaysRemainingUseCase {
    operator fun invoke(nextEpisodeAirDate: Date): Long
}