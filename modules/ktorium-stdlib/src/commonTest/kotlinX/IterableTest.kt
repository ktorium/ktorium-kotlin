package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class IterableTest {

    @Test
    fun dropFirst_emptyIterable_returnEmpty() {
        val list = emptyList<String>()

        val result = list.dropFirst()

        assertTrue(result.isEmpty())
    }

    @Test
    fun dropFirst_manyElements_returnTail() {
        val list = (1..5).toList()

        val result = list.dropFirst()

        assertEquals(listOf(2, 3, 4, 5), result)
    }

    @Test
    fun dropUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.dropUntil {
            false
        }

        assertEquals(emptyList(), result)
    }

    @Test
    fun dropUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.dropUntil {
            it == 6
        }

        assertEquals(listOf(6, 7, 8, 9, 10), result)
    }

    @Test
    fun firstOrDefault_firstEvenNumber_returnFirstPredicate() {
        val numbers = (1..10)

        val first = numbers.firstOrDefault(33, { it % 3 == 0 })

        assertEquals(3, first)
    }

    @Test
    fun firstOrElse_firstEvenNumber_returnFirstPredicate() {
        val numbers = (1..10)

        val first = numbers.firstOrElse({ 33 }, { it % 3 == 0 })

        assertEquals(3, first)
    }

    @Test
    fun firstOrElse_alwaysFalse_returnDefaultValue() {
        val numbers = (1..10)
        val value = 33

        val first = numbers.firstOrElse({ value }, { false })

        assertEquals(value, first)
    }

    @Test
    fun dropFirst_notEmpty_dropOne() {
        val numbers = (1..10)

        val tail = numbers.dropFirst()

        assertEquals(tail.size, numbers.count() - 1)
    }

    @Test
    fun takeUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.takeUntil {
            false
        }

        assertEquals(values.toList(), result)
    }

    @Test
    fun takeUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList()

        val result = values.takeUntil {
            it == 6
        }

        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }
}
