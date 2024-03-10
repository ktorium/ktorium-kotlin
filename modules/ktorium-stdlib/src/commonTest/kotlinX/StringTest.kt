package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalSince(Unreleased)
internal class StringTest {

    @Test
    fun equalsIgnoreCase_equalStrings_returnTrue() {
        val value = "value"
        val other = "VALUE"

        val result = value.equalsIgnoreCase(other)

        assertTrue(result)
    }

    @Test
    fun equalsIgnoreCase_notEqualStrings_returnTrue() {
        val value = "value"
        val other = "123"

        val result = value.equalsIgnoreCase(other)

        assertFalse(result)
    }
}
