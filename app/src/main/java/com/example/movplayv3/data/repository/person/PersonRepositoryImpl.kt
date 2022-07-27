package com.example.movplayv3.data.repository.person

import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepositoryImpl @Inject constructor(
    private val apiOthersApiHelper: TmdbOthersApiHelper
) : PersonRepository {
}