package com.example.movplayv3.utils

import androidx.room.TypeConverter
import com.example.movplayv3.data.model.movie.MovieEntityType
import com.example.movplayv3.data.model.tvshow.TvShowEntityType
import java.util.*

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

class MovieEntityTypeConverters {
    @TypeConverter
    fun toMovieEntityType(value: String) = enumValueOf<MovieEntityType>(value)

    @TypeConverter
    fun fromMovieEntityType(value: MovieEntityType) = value.name
}

class TvShowEntityTypeConverters {
    @TypeConverter
    fun toTvShowEntityType(value: String) = enumValueOf<TvShowEntityType>(value)

    @TypeConverter
    fun fromTvShowEntityType(value: TvShowEntityType) = value.name
}