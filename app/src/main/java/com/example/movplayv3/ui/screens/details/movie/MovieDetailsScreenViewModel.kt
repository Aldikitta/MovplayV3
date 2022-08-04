//package com.example.movplayv3.ui.screens.details.movie
//
//import androidx.lifecycle.SavedStateHandle
//import com.example.movplayv3.BaseViewModel
//import com.example.movplayv3.data.model.DeviceLanguage
//import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
//import com.example.movplayv3.domain.usecase.interfaces.GetDeviceLanguageUseCase
//import com.example.movplayv3.domain.usecase.interfaces.movie.*
//import com.example.movplayv3.domain.usecase.movie.GetRelatedMoviesOfTypeUseCaseImpl
//import com.example.movplayv3.domain.usecase.movie.LikeMovieUseCaseImpl
//import com.example.movplayv3.domain.usecase.movie.UnlikeMovieUseCaseImpl
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//@HiltViewModel
//class MovieDetailsScreenViewModel @Inject constructor(
//    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
//    private val getRelatedMoviesUseCase: GetRelatedMoviesOfTypeUseCaseImpl,
//    private val getMovieDetailsUseCase: GetMovieDetailsUseCaseImpl,
//    private val getMovieBackdropsUseCase: GetMovieBackdropsUseCaseImpl,
//    private val getMovieExternalIdsUseCase: GetMovieExternalIdsUseCaseImpl,
//    private val getMovieWatchProvidersUseCase: GetMovieWatchProvidersUseCaseImpl,
//    private val getMovieReviewsCountUseCase: GetMovieReviewsCountUseCaseImpl,
//    private val getMovieCreditsUseCase: GetMovieCreditUseCaseImpl,
//    private val getMoviesVideosUseCase: GetMovieVideosUseCaseImpl,
//    private val getMovieCollectionUseCase: GetMovieCollectionUseCaseImpl,
//    private val getOtherDirectorMoviesUseCase: GetOtherDirectorMoviesUseCaseImpl,
//    private val getFavoriteMoviesIdsUseCaseImpl: GetFavoriteMoviesIdsUseCaseImpl,
//    private val addRecentlyBrowsedMovieUseCase: AddRecentlyBrowsedMovieUseCaseImpl,
//    private val unlikeMovieUseCase: UnlikeMovieUseCaseImplImpl,
//    private val likeMovieUseCaseImpl: LikeMovieUseCaseImplImpl,
//    private val savedStateHandle: SavedStateHandle
//): BaseViewModel(){
//    private val navArgs: MovieDetailsScreenArgs =
//        MovieDetailsScreenDestination.argsFrom(savedStateHandle)
//    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()
//    private val favoritesMoviesIdsFlow: Flow<List<Int>> = getFavoriteMoviesIdsUseCaseImpl()
//
//}