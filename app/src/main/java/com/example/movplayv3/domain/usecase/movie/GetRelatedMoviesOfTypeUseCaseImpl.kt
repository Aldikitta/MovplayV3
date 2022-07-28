package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.RelationType
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetRelatedMoviesOfTypeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRelatedMoviesOfTypeUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetRelatedMoviesOfTypeUseCase {
    override fun invoke(
        movieId: Int,
        type: RelationType,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> {
        return when (type) {
            RelationType.Similar -> {
                movieRepository.similarMovies(
                    movieId = movieId,
                    deviceLanguage = deviceLanguage
                )
            }
            RelationType.Recommended -> {
                movieRepository.moviesRecommendation(
                    movieId = movieId,
                    deviceLanguage = deviceLanguage
                )
            }
        }
    }

}