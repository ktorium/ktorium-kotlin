package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ListTest {

    @Test
    fun dropLast_notEmpty_dropOne() {
        val numbers = (1..10).toList()

        val head = numbers.dropLast()

        assertEquals(head.size, numbers.count() - 1)
    }
}
