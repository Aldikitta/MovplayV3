package com.example.movplayv3.utils

import android.app.DatePickerDialog
import android.content.Context
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

fun createDateDialog(
    context: Context,
    initialDate: Date?,
    minDate: Date? = null,
    maxDate: Date? = null,
    onDateSelected: (Date) -> Unit = {}
): DatePickerDialog {
    val (initYear, initMonth, initDayOfMonth) = Calendar.getInstance().apply {
        time = initialDate ?: Date()
    }.run {
        Triple(get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH))
    }
}
