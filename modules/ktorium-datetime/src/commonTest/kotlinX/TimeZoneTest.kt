@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TimeZoneTest {

    @Test
    fun ofOrNull_validTimeZone_success() {
        val expected = TimeZone.of("Europe/Berlin")

        val value = TimeZone.ofOrNull("Europe/Berlin")

        assertEquals(expected, value)
    }

    @Test
    fun ofOrNull_invalidTimeZone_success() {
        assertNull(TimeZone.ofOrNull("America/Moscow"))
    }
}
