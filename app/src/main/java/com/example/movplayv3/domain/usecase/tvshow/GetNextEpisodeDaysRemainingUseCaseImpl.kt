package com.example.movplayv3.domain.usecase.tvshow

import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetNextEpisodeDaysRemainingUseCase
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class GetNextEpisodeDaysRemainingUseCaseImpl @Inject constructor() :
    GetNextEpisodeDaysRemainingUseCase {
    override fun invoke(nextEpisodeAirDate: Date): Long {
        val millionSeconds = nextEpisodeAirDate.time - Calendar.getInstance().timeInMillis
        val daysDiff = TimeUnit.MILLISECONDS.toDays(millionSeconds)
        return if (daysDiff < 0) 0 else daysDiff
    }
}