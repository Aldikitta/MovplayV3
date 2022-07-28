package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetNextEpisodeDaysRemainingUseCase
import java.util.*
import javax.inject.Inject


class GetNextEpisodeDaysRemainingUseCaseImpl @Inject constructor() :
    GetNextEpisodeDaysRemainingUseCase {
    override fun invoke(nextEpisodeAirDate: Date): Long {
        TODO("Not yet implemented")
    }
}