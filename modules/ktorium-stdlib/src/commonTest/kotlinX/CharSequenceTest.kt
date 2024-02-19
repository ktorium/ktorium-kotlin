package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumApi
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalKtoriumApi
internal class CharSequenceTest {

    @Test
    fun isNumeric_validNumber_returnsTrue() {
        assertTrue("123".isNumeric())
    }

    @Test
    fun isNumeric_invalidNumber_returnsFalse() {
        assertFalse("NaN".isNumeric())
    }
}
