@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone

private val LocalTime.Companion.MINIMUM: LocalTime
    get() = LocalTime(0, 0)

private val LocalTime.Companion.MAXIMUM: LocalTime
    get() = LocalTime(23, 59, 59, 999999999)

public fun LocalDate.getStartOfDay(): LocalDateTime =
    LocalDateTime(this, LocalTime.MINIMUM)

public fun LocalDate.getEndOfDay(): LocalDateTime {
    return LocalDateTime(this, LocalTime.MAXIMUM)
}

public fun LocalDate.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate {
    return LocalDateTime.now(timeZone).date
}

public fun LocalTime.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime {
    return LocalDateTime.now(timeZone).time
}
