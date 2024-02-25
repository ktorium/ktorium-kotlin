@file:Suppress("PackageDirectoryMismatch")
@file:OptIn(ExperimentalKtoriumAPI::class)

package org.ktorium.datetime

import kotlinx.datetime.TimeZone
import org.ktorium.kotlin.ExperimentalKtoriumAPI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TimeZoneTest {

    @Test
    fun ofOrNull_validTimeZone_success() {
        val expected = TimeZone.of("UTC")

        val value = TimeZone.ofOrNull("UTC")

        assertEquals(expected, value)
    }

    @Test
    fun ofOrNull_invalidTimeZone_success() {
        assertNull(TimeZone.ofOrNull("America/Moscow"))
    }
}
