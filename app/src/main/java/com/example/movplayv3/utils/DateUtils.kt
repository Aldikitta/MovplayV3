package com.example.movplayv3.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatted(format: String = "dd.MM.yyyy"): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}

fun Date.yearString() = formatted(format = "yyyy")

fun Date.timeString() = formatted(format = "HH:mm")

fun yearRangeString(from: Date?, to: Date?): String {
    return listOf(from, to)
        .mapNotNull { date -> date?.yearString() }
        .distinct()
        .joinToString(separator = " - ")
}
