package org.ktorium.kotlin.stdlib

import kotlin.test.*

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

    @Test
    fun ifTrue_call_returnTrue() {
        var number = 1
        val value = true

        val result = value.ifTrue { number = 3 }

        assertEquals(3, number)
        assertTrue(result)
    }

    @Test
    fun ifFalse_call_returnFalse() {
        var number = 1
        val value = false

        val result = value.ifFalse { number = 3 }

        assertEquals(3, number)
        assertFalse(result)
    }
}
