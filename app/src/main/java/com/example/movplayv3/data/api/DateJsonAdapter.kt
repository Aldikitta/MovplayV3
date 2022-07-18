package com.example.movplayv3.data.api

import android.annotation.SuppressLint
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.text.SimpleDateFormat
import java.util.*

class DateJsonAdapter : JsonAdapter<Date>() {
    private companion object{
        @SuppressLint("SimpleDateFormat")
        val serverDateFormat = SimpleDateFormat("yyyy-MM-dd")
        @SuppressLint("SimpleDateFormat")
        val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }
    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        val dateString = reader.nextString()

        if (dateString.isEmpty()) {
            return null
        }

        return null
    }

    override fun toJson(writer: JsonWriter, value: Date?) {
        TODO("Not yet implemented")
    }
}
