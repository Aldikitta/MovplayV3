package com.example.movplayv3.data.model

import com.example.movplayv3.utils.formatted
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class DateParam(
    private val date: Date
){
    override fun toString(): String {
        return date.formatted("yyyy-MM-dd")
    }
}
