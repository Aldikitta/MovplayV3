package com.example.movplayv3.utils

import java.text.NumberFormat
import java.util.*
import kotlin.time.Duration.Companion.minutes

fun Int.formattedRuntime(): String? {
    return minutes.toComponents { hours, minutes, _, _ ->
        val hoursString = if (hours > 0) "${hours}h" else null
        val minutesString = if (minutes > 0) "${minutes}m" else null

        listOfNotNull(hoursString, minutesString).run {
            if (isEmpty()) null else joinToString(separator = " ")
        }
    }
}

fun Float.singleDecimalPlaceFormatted(): String {
    return String.format("%.1f", this)
}

fun Long.formattedMoney(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).apply {
        maximumFractionDigits = 0
    }.format(this).replace(",", " ")
}