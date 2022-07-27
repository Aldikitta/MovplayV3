package com.example.movplayv3.data.repository.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.tvshow.TvShow
import com.example.movplayv3.data.model.tvshow.TvShowDetailEntity
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.data.model.tvshow.TvShowEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface TvShowRepository {
    fun discoverTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default,
        sortType: SortType = SortType.Popularity,
        sortOrder: SortOrder = SortOrder.Desc,
        genresParam: GenresParam = GenresParam(genres = emptyList()),
        watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
        voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
        onlyWithPosters: Boolean = false,
        onlyWithScore: Boolean = false,
        onlyWithOverview: Boolean = false,
        airDateRange: DateRange = DateRange()
    ): Flow<PagingData<TvShow>>

    fun topRatedTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>

    fun onTheAirTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowDetailEntity>>

    fun trendingTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>

    fun popularTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>

    fun airingTodayTvShows(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShowEntity>>

    fun similarTvShows(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShow>>

    fun tvShowsRecommendations(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<TvShow>>

    fun getTvShowDetails(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Call<TvShowDetails>

    fun tvShowImages(
        tvShowId: Int,
    ): Call<ImagesResponse>

    fun tvShowReviews(tvShowId: Int): Flow<PagingData<Review>>

    fun tvShowReview(tvShowId: Int): Flow<PagingData<ReviewsResponse>>

    fun watchProviders(tvShowId: Int): Flow<PagingData<WatchProvidersResponse>>

    fun getExternalIds(tvShowId: Int): Flow<PagingData<ExternalIds>>

    fun tvShowVideos(
        tvShowId: Int,
        isoCode: String = DeviceLanguage.default.languageCode
    ): Call<VideosResponse>
}