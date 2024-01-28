@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.ktorium.kotlin.ExperimentalKtorium
import kotlin.time.Duration

/**
 * Returns an Instant of Jan 1st 1970  00:00:00.000 UTC.
 */
@ExperimentalKtorium
public val Instant.Companion.EPOCH: Instant
    get() = fromEpochMilliseconds(0)

@ExperimentalKtorium
public fun Instant.Companion.parseOrNull(isoString: String): Instant? = runCatching { parse(isoString) }.getOrNull()

@ExperimentalKtorium
public fun Instant.toLocalDate(timeZone: TimeZone): LocalDate = toLocalDateTime(timeZone).date

@ExperimentalKtorium
public fun Instant.toLocalTime(timeZone: TimeZone): LocalTime = toLocalDateTime(timeZone).time

@ExperimentalKtorium
public fun Instant.durationSince(other: Instant): Duration = this - other

@ExperimentalKtorium
public fun Instant.durationUntil(other: Instant): Duration = other - this

/**
 * Adds a specified [time] to [this]. Returns a new [Instant]
 */
@ExperimentalKtorium
public operator fun Instant.plus(time: LocalTime): Instant = this + time.asDuration
