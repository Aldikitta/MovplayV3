package com.example.movplayv3.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
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

    return DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val date = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }.time
            onDateSelected(date)
        }, initYear, initMonth, initDayOfMonth
    ).apply {
        val datePicker = datePicker

        minDate?.let { date ->
            datePicker.minDate = date.time
        }

        maxDate?.let { date ->
            datePicker.maxDate = date.time
        }
    }
}
