package com.example.movplayv3.data.repository.person

import com.example.movplayv3.data.model.CombinedCredits
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.ExternalIds
import com.example.movplayv3.data.model.PersonDetails
import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepositoryImpl @Inject constructor(
    private val apiOthersApiHelper: TmdbOthersApiHelper
) : PersonRepository {
    override fun getPersonDetails(
        personId: Int,
        deviceLanguage: DeviceLanguage
    ): Call<PersonDetails> {
        return apiOthersApiHelper.getPersonDetails(personId, deviceLanguage.languageCode)
    }

    override fun getCombinedCredits(
        personId: Int,
        deviceLanguage: DeviceLanguage
    ): Call<CombinedCredits> {
        return apiOthersApiHelper.getCombinedCredits(personId, deviceLanguage.languageCode)
    }

    override fun getExternalIds(personId: Int, deviceLanguage: DeviceLanguage): Call<ExternalIds> {
        TODO("Not yet implemented")
    }
}