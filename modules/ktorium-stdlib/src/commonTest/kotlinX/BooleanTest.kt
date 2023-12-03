package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertSame
import kotlin.test.assertTrue

internal class BooleanTest {

    @Test
    fun orTrue_notNull_returnSameBooleanValue() {
        val condition = true

        val result = condition.orTrue()

        assertSame(condition, result)
    }

    @Test
    fun orTrue_null_returnTrue() {
        val condition: Boolean? = null

        val result = condition.orTrue()

        assertTrue(result)
    }

    @Test
    fun orFalse_notNull_returnSameBooleanValue() {
        val condition = false

        val result = condition.orFalse()

        assertSame(condition, result)
    }

    @Test
    fun orFalse_null_returnTrue() {
        val condition: Boolean? = null

        val result = condition.orFalse()

        assertFalse(result)
    }
}
