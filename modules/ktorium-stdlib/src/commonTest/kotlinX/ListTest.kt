package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ListTest {

    @Test
    fun dropLastUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList().toTypedArray()

        val result = values.dropLastUntil {
            false
        }

        assertEquals(emptyList(), result)
    }

    @Test
    fun dropLastUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList().toTypedArray()

        val result = values.dropLastUntil {
            it == 3
        }

        assertEquals(listOf(1, 2, 3), result)
    }

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

    @Test
    fun takeLastUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.takeLastUntil {
            false
        }

        assertEquals(values.toList(), result)
    }

    @Test
    fun takeLastUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.takeLastUntil {
            it == 7
        }

        assertEquals(listOf(8, 9, 10), result)
    }
}
