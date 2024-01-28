@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlin.test.Test
import kotlin.test.assertEquals

internal class YearTests {

    @Test
    fun testIsLeap() {
        val leapYears = listOf(
            2000, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, 2048,
            2052, 2056, 2060, 2064, 2068, 2072, 2076, 2080, 2084, 2088, 2092, 2096, 2104,
        )

        for (year in 1999..2107) {
            assertEquals(leapYears.contains(year), Year.isLeap(year))
        }
    }
}
