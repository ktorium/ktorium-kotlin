package org.ktorium.kotlin.stdlib.collections

import org.ktorium.kotlin.ExperimentalKtoriumApi
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalKtoriumApi
internal class IterableTest {

    @Test
    fun dropFirst_listOfItems_returnsTail() {
        val values = listOf(1, 2)

        val result = values.dropFirst()

        assertEquals(listOf(2), result)
    }

    @Test
    fun dropUntil_predicateAlwaysFalse_returnsEmpty() {
        val values = (1..10).asIterable()

        val result = values.dropUntil { false }

        assertEquals(emptyList(), result)
    }

    @Test
    fun dropUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).asIterable()

        val result = values.dropUntil { it == 6 }

        assertEquals(listOf(6, 7, 8, 9, 10), result)
    }

    @Test
    fun firstOrDefault_firstEvenNumber_returnFirstPredicate() {
        val numbers = (1..10).asIterable()

        val first = numbers.firstOrDefault({ it % 3 == 0 }, 33)

        assertEquals(3, first)
    }

    @Test
    fun firstOrDefault_predicateAlwaysFalse_returnDefault() {
        val numbers = (1..10).asIterable()
        val default = 33

        val first = numbers.firstOrDefault({ false }, default)

        assertEquals(default, first)
    }

    @Test
    fun firstOrElse_firstEvenNumber_returnFirstPredicate() {
        val numbers = (1..10).asIterable()

        val first = numbers.firstOrElse({ it % 3 == 0 }) {
            33
        }

        assertEquals(3, first)
    }

    @Test
    fun firstOrElse_predicateAlwaysFalse_returnDefaultValue() {
        val numbers = (1..10).asIterable()
        val value = 33

        val first = numbers.firstOrElse({ false }) { value }

        assertEquals(value, first)
    }

    @Test
    fun lastOrDefault_lastEvenNumber_returnFirstPredicate() {
        val numbers = (1..11).asIterable()

        val first = numbers.lastOrDefault({ it % 2 == 0 }, 33)

        assertEquals(10, first)
    }

    @Test
    fun lastOrElse_lastEvenNumber_returnFirstPredicate() {
        val numbers = (1..11).asIterable()

        val first = numbers.lastOrElse({ it % 2 == 0 }) { 33 }

        assertEquals(10, first)
    }

    @Test
    fun lastOrElse_alwaysFalse_returnDefaultValue() {
        val numbers = (1..11).asIterable()
        val value = 33

        val first = numbers.lastOrElse({ false }) { value }

        assertEquals(value, first)
    }

    @Test
    fun takeUntil_predicateNeverTrue_returnsEmpty() {
        val values = (1..10).asIterable()

        val result = values.takeUntil { false }

        assertEquals(values.toList(), result)
    }

    @Test
    fun takeUntil_predicateTrueOnThree_returnsEmpty() {
        val values = (1..10).asIterable()

        val result = values.takeUntil { it == 6 }

        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }
}
