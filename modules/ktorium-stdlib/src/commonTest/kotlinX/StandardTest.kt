package org.ktorium.kotlin.stdlib

import jdk.jshell.spi.ExecutionControl.NotImplementedException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

internal class StandardTest {

    @Test
    fun withIf_trueCondition_returnBlock() {
        val fruit = "lemon"

        val result = withIf(true, fruit) {
            fruit
        }

        assertEquals(fruit, result)
    }

    @Test
    fun withIf_falseCondition_returnNull() {
        val list = listOf("first", "last")

        @Suppress("IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")
        val result = withIf(false, list) {
            throw Exception()
        }

        assertNull(result)
    }

    @Test
    fun applyIf_trueCondition_callBlock() {
        val fruits = mutableListOf("blueberry", "apple")
        val grapes = "grapes"

        fruits.applyIf(true) {
            add(grapes)
        }

        assertTrue(fruits.contains(grapes))
    }

    @Test
    fun applyIf_falseCondition_callBlock() {
        val fruits = mutableListOf("blueberry", "apple")

        val result = fruits.applyIf(false) {
            throw IllegalStateException()
        }

        assertEquals(fruits, result)
    }

    @Test
    fun alsoIf_trueCondition_callBlock() {
        val fruits = mutableListOf("blueberry", "apple")
        val grapes = "grapes"

        fruits.alsoIf(true) {
            it.add(grapes)
        }

        assertTrue(fruits.contains(grapes))
    }

    @Test
    fun alsoIf_falseCondition_callBlock() {
        val fruits = mutableListOf("blueberry", "apple")

        val result = fruits.alsoIf(false) {
            throw IllegalStateException()
        }

        assertEquals(fruits, result)
    }

    @Test
    fun letIf_trueCondition_callBlock() {
        val fruit = "blueberry"
        val apple = "apple"

        val result = fruit.letIf(true) {
            "apple"
        }

        assertEquals(apple, result)
    }

    @Test
    fun letIf_falseCondition_callBlock() {
        val fruit = "blueberry"

        @Suppress("IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")
        fruit.letIf(false) {
            throw NotImplementedException("not implemented")
        }
    }

    @Test
    fun getOrThrow_nullValue_throwException() {
        val fruit: String? = null

        assertFailsWith(Exception::class) {
            fruit.getOrThrow(RuntimeException())
        }
    }

    @Test
    fun getOrThrow_notNullValue_returnThis() {
        val fruit = "lemon"

        val result = fruit.getOrThrow(Exception())

        assertSame(fruit, result)
    }

    @Test
    fun getOrDefault_nullValue_returnDefault() {
        val fruit: String? = null
        val defaultValue = "watermelon"

        val result = fruit.getOrDefault(defaultValue)

        assertEquals(defaultValue, result)
    }

    @Test
    fun getOrDefault_notNullValue_returnThis() {
        val fruit = "lemon"

        val result = fruit.getOrDefault("default-value")

        assertSame(fruit, result)
    }

    @Test
    fun getOrElse_nullValue_callBlock() {
        val fruit: String? = null
        val defaultValue = "watermelon"

        val result = fruit.getOrElse { defaultValue }

        assertEquals(defaultValue, result)
    }

    @Test
    fun getOrElse_notNullValue_returnThis() {
        val fruit = "lemon"

        val result = fruit.getOrElse { throw IllegalStateException() }

        assertSame(fruit, result)
    }

    @Test
    fun isNull_nullValue_returnTrue() {
        val fruit: String? = null

        assertTrue(fruit.isNull())
    }

    @Test
    fun isNull_notNullValue_returnFalse() {
        val value = "lemon".getOrDefault(null)

        assertFalse(value.isNull())
    }

    @Test
    fun isNotNull_nullValue_returnTrue() {
        val fruit: String? = null

        assertFalse(fruit.isNotNull())
    }

    @Test
    fun isNotNull_notNullValue_returnFalse() {
        val value = "lemon".getOrDefault(null)

        assertTrue(value.isNotNull())
    }
}
