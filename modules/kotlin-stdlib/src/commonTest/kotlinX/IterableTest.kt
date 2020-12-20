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
    fun lastOrElse_lastEvenNumber_returnLastPredicate() {
        val numbers = (1..10)

        val last = numbers.lastOrElse({ it % 3 == 0 }, { 33 })

        assertEquals(9, last)
    }

    @Test
    fun lastOrElse_alwaysFalse_returnDefaultValue() {
        val numbers = (1..10)
        val value = 33

        val first = numbers.lastOrElse({ false }, { value })

        assertEquals(value, first)
    }

}
