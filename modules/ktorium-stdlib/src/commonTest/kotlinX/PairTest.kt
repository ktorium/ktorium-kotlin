package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumAPI
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalKtoriumAPI
internal class PairTest {

    @Test
    fun swap_twoValues_pairSwapped() {
        val pair = 1 to 2

        val result = pair.swap()

        assertEquals(2 to 1, result)
    }
}
