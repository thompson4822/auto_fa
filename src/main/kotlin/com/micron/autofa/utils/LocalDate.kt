package com.micron.autofa.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun stringToLocalDate(string: String, format: String = "yyyy-MM-dd"): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(format)
    return LocalDate.parse(string, formatter)
}

fun longToLocalDate(value: Long): LocalDate {
    val date = Date(value)
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
}

fun LocalDate.toLong(): Long {
    val defaultZoneId = ZoneId.systemDefault()
    return Date.from(this.atStartOfDay(defaultZoneId).toInstant()).time
}