package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ListTest {

    @Test
    fun lastOrElse_lastEvenNumber_returnLastPredicate() {
        val numbers = (1..10).toList()

        val last = numbers.lastOrElse({ it % 3 == 0 }, { 33 })

        assertEquals(9, last)
    }

    @Test
    fun lastOrElse_alwaysFalse_returnDefaultValue() {
        val numbers = (1..10).toList()
        val value = 33

        val first = numbers.lastOrElse({ false }, { value })

        assertEquals(value, first)
    }

    @Test
    fun dropLast_notEmpty_dropOne() {
        val numbers = (1..10).toList()

        val head = numbers.dropLast()

        assertEquals(head.size, numbers.count() - 1)
    }
}
