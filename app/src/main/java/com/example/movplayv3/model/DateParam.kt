package com.example.movplayv3.model

import com.example.movplayv3.utils.formatted
import java.util.*

data class DateParam(private val date: Date) {
    override fun toString(): String {
        return date.formatted("yyyy-MM-dd")
    }
}
