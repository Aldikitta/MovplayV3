package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.RelationType
import com.example.movplayv3.data.model.tvshow.TvShow
import com.example.movplayv3.data.repository.tvshow.TvShowRepository
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetRelatedTvShowsOfTypeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRelatedTvShowsOfTypeUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetRelatedTvShowsOfTypeUseCase {
    override fun invoke(
        tvShowId: Int,
        type: RelationType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<TvShow>> {
        return when (type) {
            RelationType.Similar -> {
                tvShowRepository.similarTvShows(
                    tvShowId = tvShowId,
                    deviceLanguage = deviceLanguage
                )
            }
            RelationType.Recommended -> {
                tvShowRepository.tvShowsRecommendations(
                    tvShowId = tvShowId,
                    deviceLanguage = deviceLanguage
                )
            }
        }
    }
}