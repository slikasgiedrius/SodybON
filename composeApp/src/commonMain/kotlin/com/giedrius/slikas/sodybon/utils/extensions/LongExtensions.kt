package com.giedrius.slikas.sodybon.utils.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long.toNormalDate(): LocalDate {
    val instant = Instant.fromEpochMilliseconds(this)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return LocalDate(
        dateTime.year,
        dateTime.monthNumber,
        dateTime.dayOfMonth
    )
}