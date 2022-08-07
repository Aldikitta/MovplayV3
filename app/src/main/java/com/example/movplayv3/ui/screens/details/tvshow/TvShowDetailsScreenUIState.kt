package com.example.movplayv3.ui.screens.details.tvshow

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.movplayv3.data.model.WatchProviders
import com.example.movplayv3.data.model.tvshow.TvShow
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.ui.screens.details.movie.AssociatedContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class TvShowDetailsScreenUIState(
    val startRoute: String,
    val tvShowDetails: TvShowDetails?,
    val additionalTvShowDetailsInfo: AdditionalTvShowDetailsInfo,
    val associatedTvShow: AssociatedTvShow,
    val associatedContent: AssociatedContent,
    val error: String?
) {
    companion object {
        fun getDefault(startRoute: String): TvShowDetailsScreenUIState {
            return TvShowDetailsScreenUIState(
                startRoute = startRoute,
                tvShowDetails = null,
                additionalTvShowDetailsInfo = AdditionalTvShowDetailsInfo.default,
                associatedTvShow = AssociatedTvShow.default,
                associatedContent = AssociatedContent.default,
                error = null
            )
        }
    }
}

@Stable
data class AssociatedTvShow(
    val similar: Flow<PagingData<TvShow>>,
    val recommendations: Flow<PagingData<TvShow>>
) {
    companion object {
        val default: AssociatedTvShow = AssociatedTvShow(
            similar = emptyFlow(),
            recommendations = emptyFlow()
        )
    }
}

@Stable
data class AdditionalTvShowDetailsInfo(
    val isFavorite: Boolean,
    val nextEpisodeRemainingDays: Long?,
    val watchProviders: WatchProviders?,
    val reviewsCount: Int
) {
    companion object {
        val default: AdditionalTvShowDetailsInfo = AdditionalTvShowDetailsInfo(
            isFavorite = false,
            nextEpisodeRemainingDays = null,
            watchProviders = null,
            reviewsCount = 0
        )
    }
}