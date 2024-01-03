package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class CharSequenceTest {
    @Test
    fun isNumeric_validNumber_returnsTrue() {
        assertTrue("123".isNumeric())
    }

    @Test
    fun isNumeric_invalidNumber_returnsFalse() {
        assertFalse("NaN".isNumeric())
    }

    @Test
    fun isNotNumeric_validNumber_returnsFalse() {
        assertFalse("123".isNotNumeric())
    }

    @Test
    fun isNotNumeric_invalidNumber_returnsTrue() {
        assertTrue("NaN".isNotNumeric())
    }

    @Test
    fun nullIfBlank_emptyString_returnsNull() {
        assertNull("".nullIfBlank())
        assertNull(" ".nullIfBlank())
    }


}
