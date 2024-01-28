package org.ktorium.kotlin.stdlib.collections

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ListTest {

    @Test
    fun dropLastUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.dropLastUntil { false }

        assertEquals(emptyList(), result)
    }

    @Test
    fun dropLastUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.dropLastUntil { it == 3 }

        assertEquals(listOf(1, 2, 3), result)
    }

    @Test
    fun lastOrDefault_lastEvenNumber_returnFirstPredicate() {
        val numbers = (1..11).toList()

        val first = numbers.lastOrDefault({ it % 2 == 0 }, 33)

        assertEquals(10, first)
    }

    @Test
    fun lastOrElse_lastEvenNumber_returnLastPredicate() {
        val numbers = (1..11).toList()

        val last = numbers.lastOrElse({ it % 2 == 0 }, { 33 })

        assertEquals(10, last)
    }

    @Test
    fun lastOrElse_alwaysFalse_returnDefaultValue() {
        val numbers = (1..11).toList()
        val value = 33

        val first = numbers.lastOrElse({ false }, { value })

        assertEquals(value, first)
    }

    @Test
    fun takeLastUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.takeLastUntil { false }

        assertEquals(values.toList(), result)
    }

    @Test
    fun takeLastUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.takeLastUntil { it == 7 }

        assertEquals(listOf(8, 9, 10), result)
    }

    @Test
    fun takeUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.takeUntil { false }

        assertEquals(values.toList(), result)
    }

    @Test
    fun takeUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.takeUntil { it == 6 }

        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }
}
