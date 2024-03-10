@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.LocalDateTime
import org.ktorium.kotlin.ExperimentalSince
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalSince::class)
class LocalDateTimeTest {

    @Test
    fun testParseOrNull() {
        val expected = LocalDateTime.parse("2019-10-01T18:43:15")

        val value = LocalDateTime.parseOrNull("2019-10-01T18:43:15")

        assertEquals(expected, value)
    }
}
