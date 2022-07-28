package com.example.movplayv3.domain.usecase.interfaces.tvshow

import java.util.*

interface GetNextEpisodeDaysRemainingUseCaseImpl {
    operator fun invoke(nextEpisodeAirDate: Date): Long
}