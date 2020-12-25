package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class CollectionTest {
    @Test
    fun ifNotEmpty_empty_returnEmptyList() {
        val values = emptyList<String>()

        val result = values.ifNotEmpty { throw Exception() }

        assertNull(result)
    }

    @Test
    fun ifNotEmpty_nonEmpty_returnsValues() {
        val values = (1..10).toList()

        val result = values.ifNotEmpty { filter { it % 2 == 0 } }

        assertEquals(listOf(2, 4, 6, 8, 10), result)
    }
}
