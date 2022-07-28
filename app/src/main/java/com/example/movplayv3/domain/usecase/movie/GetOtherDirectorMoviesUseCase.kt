package com.example.movplayv3.domain.usecase.interfaces.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.CrewMember
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface GetOtherDirectorMoviesUseCase {
    operator fun invoke(
        mainDirector: CrewMember,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>>
}