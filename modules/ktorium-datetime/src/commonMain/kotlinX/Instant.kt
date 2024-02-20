@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.ktorium.kotlin.ExperimentalKtoriumAPI
import kotlin.time.Duration

/**
 * Returns an Instant of Jan 1st 1970  00:00:00.000 UTC.
 */
@ExperimentalKtoriumAPI
public val Instant.Companion.EPOCH: Instant
    get() = fromEpochMilliseconds(0)

@ExperimentalKtoriumAPI
public fun Instant.Companion.parseOrNull(isoString: String): Instant? = runCatching { parse(isoString) }.getOrNull()

@ExperimentalKtoriumAPI
public fun Instant.toLocalDate(timeZone: TimeZone): LocalDate = toLocalDateTime(timeZone).date

@ExperimentalKtoriumAPI
public fun Instant.toLocalTime(timeZone: TimeZone): LocalTime = toLocalDateTime(timeZone).time

@ExperimentalKtoriumAPI
public fun Instant.durationSince(other: Instant): Duration = this - other

@ExperimentalKtoriumAPI
public fun Instant.durationUntil(other: Instant): Duration = other - this

/**
 * Adds a specified [time] to [this]. Returns a new [Instant]
 */
@ExperimentalKtoriumAPI
public operator fun Instant.plus(time: LocalTime): Instant = this + time.asDuration
