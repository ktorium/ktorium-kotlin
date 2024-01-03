package org.ktorium.kotlin.stdlib.collections

import kotlin.test.Test
import kotlin.test.assertEquals

internal class MutableMapTest {

    @Test
    fun computeIfAbsent_absentValue_shouldExist() {
        val map = mutableMapOf<String, String>()
        val value = "value"

        val result = map.computeIfAbsent("key") { value }

        assertEquals("value", result)
    }
}
