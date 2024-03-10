@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.ktorium.kotlin.ExperimentalSince
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalSince::class)
class LocalDateTest {
    @Test
    fun startOfDate_valid_validStartOfDay() {
        val expected = LocalDateTime(2023, 12, 25, 0, 0)

        val localDate = LocalDate(2023, 12, 25).asStartOfDay()

        assertEquals(expected, localDate)
    }

    @Test
    fun endOfDate_valid_validEndOfDay() {
        val expected = LocalDateTime(2023, 12, 25, 23, 59, 59, 999_999_999)

        val localDate = LocalDate(2023, 12, 25).asEndOfDay()

        assertEquals(expected, localDate)
    }

    @Test
    fun parseOrNull_validDate_success() {
        val expected = LocalDate.parse("2019-10-01")

        val value = LocalDate.parseOrNull("2019-10-01")

        assertEquals(expected, value)
    }
}
