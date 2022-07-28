package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.CrewMember
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetOtherDirectorMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOtherDirectorMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetOtherDirectorMoviesUseCase {
    override fun invoke(
        mainDirector: CrewMember,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> {
        return movieRepository.moviesOfDirector(
            directorId = mainDirector.id,
            deviceLanguage = deviceLanguage
        )
    }
}