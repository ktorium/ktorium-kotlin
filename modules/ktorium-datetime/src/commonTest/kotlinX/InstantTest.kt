@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import org.ktorium.kotlin.ExperimentalSince
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalSince::class)
class InstantTest {

    @Test
    fun testDurationSince() {
        val expected = 1.seconds

        val value = Instant.fromEpochSeconds(1).durationSince(Instant.fromEpochSeconds(0))

        assertEquals(expected, value)
    }

    @Test
    fun testDurationUntil() {
        val expected = 1.seconds

        val value = Instant.fromEpochSeconds(0).durationUntil(Instant.fromEpochSeconds(1))

        assertEquals(expected, value)
    }

    @Test
    fun testParseOrNull() {
        val expected = Instant.parse("1970-01-01T00:00:00Z")

        val value = Instant.parseOrNull("1970-01-01T00:00:00Z")

        assertEquals(expected, value)
    }

    @Test
    fun parseOrNull_invalidValue_returnNull() {
        val value = Instant.parseOrNull("x")

        assertNull(value)
    }

    @Test
    fun testToLocalDate() {
        val expected = LocalDate(1970, 1, 1)

        val value = Instant.fromEpochSeconds(0).toLocalDate(TimeZone.UTC)

        assertEquals(expected, value)
    }

    @Test
    fun testToLocalTime() {
        val expected = LocalTime(0, 0)

        val value = Instant.fromEpochSeconds(0).toLocalTime(TimeZone.UTC)

        assertEquals(expected, value)
    }
}

