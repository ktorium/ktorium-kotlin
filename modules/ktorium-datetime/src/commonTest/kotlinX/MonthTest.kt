@file:Suppress("PackageDirectoryMismatch")
@file:OptIn(ExperimentalKtoriumAPI::class)

package org.ktorium.datetime

import kotlinx.datetime.Month
import org.ktorium.kotlin.ExperimentalKtoriumAPI
import kotlin.test.Test
import kotlin.test.assertEquals

class MonthTest {

    @Test
    fun daysIn_validMonth_success() {
        assertEquals(31, Month.JANUARY.daysIn(2000))
        assertEquals(29, Month.FEBRUARY.daysIn(2000))
        assertEquals(28, Month.FEBRUARY.daysIn(2001))
        assertEquals(31, Month.MARCH.daysIn(2000))
        assertEquals(30, Month.APRIL.daysIn(2000))
        assertEquals(31, Month.MAY.daysIn(2000))
        assertEquals(30, Month.JUNE.daysIn(2000))
        assertEquals(31, Month.JULY.daysIn(2000))
        assertEquals(31, Month.AUGUST.daysIn(2000))
        assertEquals(30, Month.SEPTEMBER.daysIn(2000))
        assertEquals(31, Month.OCTOBER.daysIn(2000))
        assertEquals(30, Month.NOVEMBER.daysIn(2000))
        assertEquals(31, Month.DECEMBER.daysIn(2000))
    }
}
