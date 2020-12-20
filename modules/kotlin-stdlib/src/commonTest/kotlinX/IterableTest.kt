package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertEquals

internal class IterableTest {

    @Test
    fun firstOrElse_firstEvenNumber_returnFirstPredicate() {
        val numbers = (1..10)

        val first = numbers.firstOrElse({ it % 3 == 0 }, { 33 })

        assertEquals(3, first)
    }

    @Test
    fun firstOrElse_alwaysFalse_returnDefaultValue() {
        val numbers = (1..10)
        val value = 33

        val first = numbers.firstOrElse({ false }, { value })

        assertEquals(value, first)
    }

    @Test
    fun dropFirst_notEmpty_dropOne() {
        val numbers = (1..10)

        val tail = numbers.dropFirst()

        assertEquals(tail.size, numbers.count() - 1)
    }
}
