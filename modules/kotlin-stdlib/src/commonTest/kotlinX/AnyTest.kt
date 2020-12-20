package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.fail

internal class AnyTest {

    @Test
    fun ifNull_notNullTarget_returnThis() {
        val value = 3

        val result = value.ifNull { fail("Value is not null") }

        assertSame(value, result)
    }

    @Test
    fun ifNull_nullTarget_returnDefault() {
        val value = null
        val expected = 33

        val result = value.ifNull { expected }

        assertSame(expected, result)
    }
}
