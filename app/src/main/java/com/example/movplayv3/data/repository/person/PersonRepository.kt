package com.example.movplayv3.data.repository.person

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.PersonDetails
import retrofit2.Call

interface PersonRepository {
    fun getPersonDetails(
        personId: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ) : Call<PersonDetails>
}