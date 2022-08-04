package com.example.movplayv3.di

import com.example.movplayv3.domain.usecase.*
import com.example.movplayv3.domain.usecase.interfaces.*
import com.example.movplayv3.domain.usecase.interfaces.movie.*
import com.example.movplayv3.domain.usecase.interfaces.tvshow.*
import com.example.movplayv3.domain.usecase.movie.*
import com.example.movplayv3.domain.usecase.tvshow.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseBinds {
    @Binds
    fun provideAddRecentlyBrowsedMovieUseCase(
        impl: AddRecentlyBrowsedMovieUseCaseImpl
    ): AddRecentlyBrowsedMovieUseCase

    @Binds
    fun provideClearRecentlyBrowsedMoviesUseCase(
        impl: ClearRecentlyBrowsedMoviesUseCaseImpl
    ): ClearRecentlyBrowsedMoviesUseCase

    @Binds
    fun provideGetAiringTodayTvShowUseCase(
        impl: GetAiringTodayTvShowsUseCaseImpl
    ): GetAiringTodayTvShowsUseCase

    @Binds
    fun provideGetAllMoviesWatchProvidersUseCase(
        impl: GetAllMoviesWatchProvidersUseCaseImpl
    ): GetAllMoviesWatchProvidersUseCase

    @Binds
    fun provideGetDeviceLanguageUseCase(
        impl: GetDeviceLanguageUseCaseImpl
    ): GetDeviceLanguageUseCase

    @Binds
    fun provideGetDiscoverAllMoviesUseCase(
        impl: GetDiscoverAllMoviesUseCaseImpl
    ): GetDiscoverAllMoviesUseCase

    @Binds
    fun provideGetDiscoverAllTvShowsUseCase(
        impl: GetDiscoverAllTvShowsUseCaseImpl
    ): GetDiscoverAllTvShowsUseCase

    @Binds
    fun provideGetDiscoverMoviesUseCase(
        impl: GetDiscoverMoviesUseCaseImpl
    ): GetDiscoverMoviesUseCase

    @Binds
    fun provideGetDiscoverTvShowsUseCase(
        impl: GetDiscoverTvShowsUseCaseImpl
    ): GetDiscoverTvShowsUseCase

    @Binds
    fun provideGetEpisodeStillsUseCase(
        impl: GetEpisodeStillsUseCaseImpl
    ): GetEpisodeStillsUseCase

    @Binds
    fun provideGetFavoriteMoviesIdsUseCase(
        impl: GetFavoriteMoviesIdsUseCaseImpl
    ): GetFavoriteMoviesIdsUseCase

    @Binds
    fun provideGetFavoritesMovieCountUseCase(
        impl: GetFavoritesMovieCountUseCaseImpl
    ): GetFavoritesMovieCountUseCase

    @Binds
    fun provideGetFavoritesMoviesUseCase(
        impl: GetFavoritesMoviesUseCaseImpl
    ): GetFavoritesMoviesUseCase

    @Binds
    fun provideGetFavoritesTvShowsUseCase(
        impl: GetFavoritesTvShowsUseCaseImpl
    ): GetFavoritesTvShowsUseCase

    @Binds
    fun provideGetFavoritesUseCase(
        impl: GetFavoritesUseCaseImpl
    ): GetFavoritesUseCase

    @Binds
    fun provideGetFavoriteTvShowsCountUseCase(
        impl: GetFavoriteTvShowsCountUseCaseImpl
    ): GetFavoriteTvShowsCountUseCase

    @Binds
    fun provideGetMediaMultiSearchUseCase(
        impl: GetMediaMultiSearchUseCaseImpl
    ): GetMediaMultiSearchUseCase

    @Binds
    fun provideGetMediaTypeReviewsUseCase(
        impl: GetMediaTypeReviewsUseCaseImpl
    ): GetMediaTypeReviewsUseCase

    @Binds
    fun provideMovieBackdropsUseCase(
        impl: GetMovieBackdropsUseCaseImpl
    ): GetMovieBackdropsUseCase

    @Binds
    fun provideGetMovieCollectionUseCase(
        impl: GetMovieCollectionUseCaseImpl
    ): GetMovieCollectionUseCase

    @Binds
    fun provideGetMovieCreditsUseCase(
        impl: GetMovieCreditUseCaseImpl
    ): GetMovieCreditUseCase

    @Binds
    fun provideGetMovieDetailsUseCase(
        impl: GetMovieDetailsUseCaseImpl
    ): GetMovieDetailsUseCase

    @Binds
    fun provideGetMovieExternalIdsUseCase(
        impl: GetMovieExternalIdsUseCaseImpl
    ): GetMovieExternalIdsUseCase

    @Binds
    fun provideGetMovieGenresUseCase(
        impl: GetMovieGenresUseCaseImpl
    ): GetMovieGenresUseCase

    @Binds
    fun provideGetMovieReviewsCountUseCase(
        impl: GetMovieReviewsCountUseCaseImpl
    ): GetMovieReviewsCountUseCase

    @Binds
    fun provideGetMoviesOfTypeUseCase(
        impl: GetMoviesOfTypeUseCaseImpl
    ): GetMoviesOfTypeUseCase

    @Binds
    fun provideGetMovieVideosUseCase(
        impl: GetMovieVideosUseCaseImpl
    ): GetMovieVideosUseCase

    @Binds
    fun provideGetMovieWatchProvidersUseCase(
        impl: GetMovieWatchProvidersUseCaseImpl
    ): GetMovieWatchProvidersUseCase

    @Binds
    fun provideGetNowPlayingMoviesUseCase(
        impl: GetNowPlayingMoviesUseCaseImpl
    ): GetNowPlayingMoviesUseCase

    @Binds
    fun provideGetOnTheAirTvShowsUseCase(
        impl: GetOnTheAirTvShowsUseCaseImpl
    ): GetOnTheAirTvShowsUseCase

    @Binds
    fun provideGetOtherDirectorMoviesUseCase(
        impl: GetOtherDirectorMoviesUseCaseImpl
    ): GetOtherDirectorMoviesUseCase

    @Binds
    fun provideGetPopularMoviesUseCase(
        impl: GetPopularMoviesUseCaseImpl
    ): GetPopularMoviesUseCase

    @Binds
    fun provideGetRecentlyBrowsedMoviesUseCase(
        impl: GetRecentlyBrowsedMoviesUseCaseImpl
    ): GetRecentlyBrowsedMoviesUseCase

    @Binds
    fun provideGetRecentlyBrowsedTvShowsUseCase(
        impl: GetRecentlyBrowsedTvShowsUseCaseImpl
    ): GetRecentlyBrowsedTvShowsUseCase

    @Binds
    fun provideGetRelatedMoviesOfTypeUseCase(
        impl: GetRelatedMoviesOfTypeUseCaseImpl
    ): GetRelatedMoviesOfTypeUseCase

    @Binds
    fun provideGetRelatedTvShowsOfTypeUseCase(
        impl: GetRelatedTvShowsOfTypeUseCaseImpl
    ): GetRelatedTvShowsOfTypeUseCase

    @Binds
    fun provideGetSeasonCreditsUseCase(
        impl: GetSeasonCreditsUseCaseImpl
    ): GetSeasonCreditsUseCase

    @Binds
    fun provideGetSeasonDetailsUseCase(
        impl: GetSeasonDetailsUseCaseImpl
    ): GetSeasonDetailsUseCase

    @Binds
    fun provideGetSeasonVideosUseCase(
        impl: GetSeasonsVideosUseCaseImpl
    ): GetSeasonsVideosUseCase

    @Binds
    fun provideGetSpeechToTextAvailableUseCase(
        impl: GetSpeechToTextAvailableUseCaseImpl
    ): GetSpeechToTextAvailableUseCase

    @Binds
    fun provideCameraAvailableUseCase(
        impl: GetCameraAvailableUseCaseImpl
    ): GetCameraAvailableUseCase

    @Binds
    fun provideGetTopRatedMoviesUseCase(
        impl: GetTopRatedMoviesUseCaseImpl
    ): GetTopRatedMoviesUseCase

    @Binds
    fun provideGetTopRatedTvShowsUseCase(
        impl: GetTopRatedTvShowsUseCaseImpl
    ): GetTopRatedTvShowsUseCase

//    @Binds
//    fun provideGetTrendingMoviesUseCase(
//        impl: GetTrendingMoviesUseCaseImpl
//    ): GetTrendingMoviesUseCase

    @Binds
    fun provideGetTrendingTvShowsUseCase(
        impl: GetTrendingTvShowsUseCaseImpl
    ): GetTrendingTvShowsUseCase

    @Binds
    fun provideGetTvShowGenresUseCase(
        impl: GetTvShowGenresUseCaseImpl
    ): GetTvShowGenresUseCase

    @Binds
    fun provideGetTvShowOfTypeUseCase(
        impl: GetTvShowOfTypeUseCaseImpl
    ): GetTvShowOfTypeUseCase

    @Binds
    fun provideGetUpcomingMoviesUseCase(
        impl: GetUpcomingMoviesUseCaseImpl
    ): GetUpcomingMoviesUseCase

    @Binds
    fun provideLikeMovieUseCase(
        impl: LikeMovieUseCaseImpl
    ): LikeMovieUseCase

    @Binds
    fun provideMediaAddSearchQueryUseCase(
        impl: MediaAddSearchQueryUseCaseImpl
    ): MediaAddSearchQueryUseCase

    @Binds
    fun provideMediaSearchQueriesUseCase(
        impl: MediaSearchQueriesUseCaseImpl
    ): MediaSearchQueriesUseCase

    @Binds
    fun provideUnlikeMovieUseCase(
        impl: UnlikeMovieUseCaseImpl
    ): UnlikeMovieUseCase

    @Binds
    fun provideGetAllTvShowsWatchProvidersUseCase(
        impl: GetAllTvShowsWatchProvidersUseCaseImpl
    ): GetAllTvShowsWatchProvidersUseCase

    @Binds
    fun providesGetPersonDetailsUseCase(
        impl: GetPersonDetailsUseCaseImpl
    ): GetPersonDetailsUseCase

    @Binds
    fun providesGetCombinedCreditsUseCase(
        impl: GetCombinedCreditsUseCaseImpl
    ): GetCombinedCreditsUseCase

    @Binds
    fun providesGetPersonExternalIdsUseCase(
        impl: GetPersonExternalIdsUseCaseImpl
    ): GetPersonExternalIdsUseCase

    @Binds
    fun providesGetTvShowDetailsUseCase(
        impl: GetTvShowDetailsUseCaseImpl
    ): GetTvShowDetailsUseCase

    @Binds
    fun providesGetNextEpisodeDaysRemainingUseCase(
        impl: GetNextEpisodeDaysRemainingUseCaseImpl
    ): GetNextEpisodeDaysRemainingUseCase

    @Binds
    fun providesGetTvShowExternalIdsUseCase(
        impl: GetTvShowExternalIdsUseCaseImpl
    ): GetTvShowExternalIdsUseCase

    @Binds
    fun providesGetTvShowImagesUseCase(
        impl: GetTvShowImagesUseCaseImpl
    ): GetTvShowImagesUseCase

    @Binds
    fun providesGetTvShowReviewsCountUseCase(
        impl: GetTvShowReviewsCountUseCaseImpl,
    ): GetTvShowReviewsCountUseCase

    @Binds
    fun providesGetTvShowVideosUseCase(
        impl: GetTvShowVideosUseCaseImpl
    ): GetTvShowVideosUseCase

    @Binds
    fun providesGetTvShowWatchProvidersUseCase(
        impl: GetTvShowWatchProvidersUseCaseImpl
    ): GetTvShowWatchProvidersUseCase

    @Binds
    fun providesAddRecentlyBrowsedTvShowUseCase(
        impl: AddRecentlyBrowsedTvShowUseCaseImpl
    ): AddRecentlyBrowsedTvShowUseCase

    @Binds
    fun providesGetFavoritesTvShowIdsUseCase(
        impl: GetFavoriteTvShowIdsUseCaseImpl
    ): GetFavoriteTvShowIdsUseCase

    @Binds
    fun providesLikeTvShowUseCase(
        impl: LikeTvShowUseCaseImpl
    ): LikeTvShowUseCase

    @Binds
    fun providesUnlikeTvShowUseCase(
        impl: UnlikeTvShowUseCaseImpl
    ): UnlikeTvShowUseCase

    @Binds
    fun providesScanBitmapForTextUseCase(
        impl: ScanBitmapForTextUseCaseImpl
    ): ScanBitmapForTextUseCase
}