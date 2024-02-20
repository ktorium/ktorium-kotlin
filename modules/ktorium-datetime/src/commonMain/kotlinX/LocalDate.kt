@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import org.ktorium.kotlin.ExperimentalKtoriumAPI

@ExperimentalKtoriumAPI
public fun LocalDate.Companion.parseOrNull(isoString: String): LocalDate? = runCatching { parse(isoString) }.getOrNull()

/**
 * Get the current date using the specified [timeZone].
 */
@ExperimentalKtoriumAPI
public fun LocalDate.Companion.now(timeZone: TimeZone): LocalDate = LocalDateTime.now(timeZone).date

/**
 * Returns a new LocalDate with specified [days] added.
 */
@ExperimentalKtoriumAPI
public fun LocalDate.plusDays(days: Int): LocalDate = plus(days, DateTimeUnit.DAY)

/**
 * Returns a new LocalDate with the day of month set to the specified [day]
 */
@ExperimentalKtoriumAPI
public fun LocalDate.withDayOfMonth(day: Int): LocalDate = LocalDate(year, month, day)

/**
 * Returns a new [LocalDateTime] set to the start of the day.
 */
@ExperimentalKtoriumAPI
public fun LocalDate.asStartOfDay(): LocalDateTime = LocalDateTime(year, month, dayOfMonth, 0, 0, 0, 0)

@ExperimentalKtoriumAPI
public fun LocalDate.asEndOfDay(): LocalDateTime = LocalDateTime(this, LocalTime.MAX)
