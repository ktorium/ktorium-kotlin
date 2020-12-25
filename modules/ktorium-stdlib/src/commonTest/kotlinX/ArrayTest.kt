package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class ArrayTest {

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
    fun dropUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList().toTypedArray()

        val result = values.dropUntil {
            false
        }

        assertEquals(emptyList(), result)
    }

    @Test
    fun dropUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList().toTypedArray()

        val result = values.dropUntil {
            it == 6
        }

        assertEquals(listOf(6, 7, 8, 9, 10), result)
    }

    @Test
    fun ifNotEmpty_empty_returnEmptyList() {
        val values = emptyArray<String>()

        val result = values.ifNotEmpty { throw Exception() }

        assertNull(result)
    }

    @Test
    fun ifNotEmpty_nonEmpty_returnsValues() {
        val values = (1..10).toList().toTypedArray()

        val result = values.ifNotEmpty { filter { it % 2 == 0 } }

        assertEquals(listOf(2, 4, 6, 8, 10), result)
    }

    @Test
    fun takeLastUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList().toTypedArray()

        val result = values.takeLastUntil {
            false
        }

        assertEquals(values.toList(), result)
    }

    @Test
    fun takeLastUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList().toTypedArray()

        val result = values.takeLastUntil {
            it == 7
        }

        assertEquals(listOf(8, 9, 10), result)
    }

    @Test
    fun takeUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).toList().toTypedArray()

        val result = values.takeUntil {
            false
        }

        assertEquals(values.toList(), result)
    }

    @Test
    fun takeUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).toList().toTypedArray()

        val result = values.takeUntil {
            it == 6
        }

        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }
}
