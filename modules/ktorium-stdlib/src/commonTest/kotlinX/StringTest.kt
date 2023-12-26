package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class StringTest {

    @Test
    fun equalsIgnoreCase_equalStrings_returnTrue() {
        val value = "lemon"
        val other = "LEMON"

        val result = value.equalsIgnoreCase(other)

        assertTrue(result)
    }

    @Test
    fun equalsIgnoreCase_notQqualStrings_returnTrue() {
        val value = "lemon"
        val other = "123"

        val result = value.equalsIgnoreCase(other)

        assertFalse(result)
    }
}
