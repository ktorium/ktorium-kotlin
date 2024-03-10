@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

@ExperimentalSince(Unreleased)
public fun LocalDateTime.Companion.parseOrNull(isoString: String): LocalDateTime? = runCatching { parse(isoString) }.getOrNull()

/**
 * Creates a new [Instant] and converts it to [LocalDateTime]
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.Companion.now(timeZone: TimeZone): LocalDateTime = Clock.System.now().toLocalDateTime(timeZone)

/**
 * Returns a new [LocalDateTime] with the given [month] and everything else of [this].
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.withMonth(month: Month): LocalDateTime = LocalDateTime(year, month, dayOfMonth, hour, minute, second, nanosecond)

/**
 * Returns a new [LocalDateTime] with the given [day] of month and everything else of [this].
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.withDayOfMonth(day: Int): LocalDateTime = LocalDateTime(year, month, day, hour, minute, second, nanosecond)

/**
 * Adds a given value to the [this], converting it to [Instant] for the addition.
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.plus(value: Int, unit: DateTimeUnit, zone: TimeZone): LocalDateTime = toInstant(zone).plus(value, unit, zone).toLocalDateTime(zone)

/**
 * Adds a specified number of [months] to [this]. Returns a new [LocalDateTime]
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.plusMonths(months: Int, zone: TimeZone): LocalDateTime = plus(months, DateTimeUnit.MONTH, zone)

/**
 * Adds a specified number of [days] to [this].
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.plusDays(days: Int, zone: TimeZone): LocalDateTime = plus(days, DateTimeUnit.DAY, zone)

/**
 * Subtracts a specified number of [months] from [this]. Returns a new [LocalDateTime]
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.minusMonths(months: Int, zone: TimeZone): LocalDateTime = plus(-months, DateTimeUnit.MONTH, zone)

/**
 * Subtracts a specified number of [days] from [this].
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.minusDays(days: Int, zone: TimeZone): LocalDateTime = plus(-days, DateTimeUnit.DAY, zone)

/**
 * Returns a new LocalDateTime that is set to the first day of year and [00:00:00.000] of the year of [this].
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.asStartOfYear(): LocalDateTime = LocalDateTime(year, Month.JANUARY, 1, 0, 0, 0, 0)

/**
 * Returns a new LocalDateTime that is set to the first day of month and [00:00:00.000] of the month of [this].
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.asStartOfMonth(): LocalDateTime = LocalDateTime(year, month, 1, 0, 0, 0, 0)

/**
 * Returns a new LocalDateTime that is set to [DayOfWeek.MONDAY] and [00:00:00.000] of the week of [this].
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.asStartOfWeek(zone: TimeZone): LocalDateTime = withPreviousOrSameDayOfWeek(DayOfWeek.MONDAY, zone).asStartOfDay()

/**
 * Returns a new LocalDateTime that has either the same (if [this] has the same day of week as [dayOfWeek]) or the previous [dayOfWeek].
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.withPreviousOrSameDayOfWeek(
    dayOfWeek: DayOfWeek,
    zone: TimeZone,
): LocalDateTime {
    val daysDiff = dayOfWeek.isoDayNumber - this.dayOfWeek.isoDayNumber
    if (daysDiff == 0) return this

    val daysToAdd = if (daysDiff >= 0) 7 - daysDiff else -daysDiff
    return minusDays(daysToAdd, zone)
}

/**
 * Returns a new [LocalDateTime] set to the start of the day.
 */
@ExperimentalSince(Unreleased)
public fun LocalDateTime.asStartOfDay(): LocalDateTime = LocalDateTime(year, month, dayOfMonth, 0, 0, 0, 0)

@ExperimentalSince(Unreleased)
public fun LocalDateTime.asEndOfDay(): LocalDateTime = LocalDateTime(this.date, LocalTime.MAX)
