package org.ktorium.kotlin.stdlib

import kotlin.test.*

internal class StandardTest {

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
    fun getOrThrow_nullValue_throwException() {
        val fruit: String? = null

        assertFailsWith(Exception::class) {
            fruit.getOrThrow(Exception())
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
    fun ifNull_nullValue_callBlock() {
        val fruit: String? = null
        val value = "watermelon"

        val result = fruit.ifNull { value }

        assertSame(value, result)
    }

    @Test
    fun ifNull_notNullValue_callBlock() {
        val fruit = "watermelon"

        val result = fruit.ifNull { throw IllegalStateException() }

        assertSame(fruit, result)
    }

    @Test
    fun isNull_nullValue_returnTrue() {
        val result = "value".isNull()

        assertFalse(result)
    }

    @Test
    fun isNull_notNullValue_returnFalse() {
        val result = null.isNull()

        assertTrue(result)
    }

    @Test
    fun isNotNull_nullValue_returnFalse() {
        val result = "value".isNotNull()

        assertTrue(result)
    }

    @Test
    fun isNull_notNullValue_returnTrue() {
        val result = null.isNotNull()

        assertFalse(result)
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

        val result = fruit.letIf(false) {
            throw IllegalStateException()
        }

        assertNull(result)
    }

    @Test
    fun orElse_nullInvoke_callBlock() {
        val peach = "peach"

        val result = { null }.orElse { peach }

        assertSame(peach, result)
    }

    @Test
    fun orElse_notNullInvoke_callBlock() {
        val lime = "lime"

        val result = { lime }.orElse { throw IllegalStateException() }

        assertSame(lime, result)
    }

    @Test
    fun runIf_trueCondition_callBlock() {
        val fruit = "blueberry"
        val apple = "apple"

        val result = fruit.runIf(true) {
            "apple"
        }

        assertEquals(apple, result)
    }

    @Test
    fun runIf_falseCondition_callBlock() {
        val fruit = "blueberry"

        val result = fruit.runIf(false) {
            throw IllegalStateException()
        }

        assertNull(result)
    }

    @Test
    fun tryOrNull_throwsException_returnNull() {
        val result = tryOrNull {
            throw Exception()
        }

        assertNull(result)
    }

    @Test
    fun tryOrNull_callBlock_returnBlock() {
        val value = "pineapple"

        val result = tryOrNull {
            value
        }

        assertSame(value, result)
    }

    @Test
    fun trySilently_noException_doNothing() {
        val value: String?
        val orange = "orange"

        trySilently { value = orange }

        assertEquals(orange, value)
    }

    @Test
    fun trySilently_withException_doNothing() {
        trySilently { throw Exception() }
    }

    @Test
    fun tryOrDefault_blockThrowException_returnDefault() {
        val value = "pineapple"

        val result = tryOrDefault(value) {
            throw Exception()
        }

        assertSame(value, result)
    }

    @Test
    fun tryOrDefault_blockReturnValue_returnValue() {
        val value = "pineapple"

        val result = tryOrDefault(value) {
            value
        }

        assertSame(value, result)
    }

    @Test
    fun tryOrElse_blockThrowException_returnDefault() {
        val value = "pineapple"

        val result = tryOrElse({ value }) {
            throw Exception()
        }

        assertSame(value, result)
    }

    @Test
    fun tryOrElse_blockReturnsSuccessful_returnValue() {
        val value = "pineapple"

        val result = tryOrElse({ throw Exception() }) {
            value
        }

        assertSame(value, result)
    }

    @Test
    fun withIf_trueCondition_returnBlock() {
        val list = listOf("first", "last")

        val result = withIf(list, true) {
            this.last()
        }

        assertEquals("last", result)
    }

    @Test
    fun withIf_falseCondition_returnNull() {
        val list = listOf("first", "last")

        val result = withIf(list, false) {
            throw Exception()
        }

        assertNull(result)
    }
}
