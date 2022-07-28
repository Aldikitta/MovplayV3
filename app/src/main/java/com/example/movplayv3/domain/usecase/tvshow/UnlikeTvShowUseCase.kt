package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.tvshow.TvShowDetails

interface UnlikeTvShowUseCase {
    operator fun invoke(details: TvShowDetails)
}