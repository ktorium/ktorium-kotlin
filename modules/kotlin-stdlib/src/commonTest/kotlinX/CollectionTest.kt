package org.ktorium.kotlin.stdlib

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CollectionTest {

    @Test
    fun ifNotEmpty_notEmptyCollection_returnBody(){
        val fruits = listOf("Apple", "Apricots", "Blackberries")
        val cherries = listOf("Cherries")

        val result = fruits.ifNotEmpty { cherries }

        assertEquals(cherries, result)
    }
}
