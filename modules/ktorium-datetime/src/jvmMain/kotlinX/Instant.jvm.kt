@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * Returns a new [Instant] with given number of [days] added
 */
public fun Instant.plusDays(days: Long): Instant = plus(days, ChronoUnit.DAYS)

/**
 * Returns a new [Instant] with given number of [days] subtracted
 */
public fun Instant.minusDays(days: Long): Instant = minus(days, ChronoUnit.DAYS)
