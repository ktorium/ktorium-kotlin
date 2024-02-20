package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumAPI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

@ExperimentalKtoriumAPI
internal class StandardTest {

    @Test
    fun applyIf_trueCondition_callBlock() {
        val values = mutableListOf<String>()
        val defaultValue = "default-value"

        values.applyIf(true) {
            add(defaultValue)
        }

        assertTrue(values.contains(defaultValue))
    }

    @Test
    fun applyIf_falseCondition_callBlock() {
        val values = mutableListOf<String>()

        val result = values.applyIf(false) {
            throw IllegalStateException()
        }

        assertEquals(values, result)
    }

    @Test
    fun alsoIf_trueCondition_callBlock() {
        val values = mutableListOf<String>()
        val defaultValue = "default-value"

        values.alsoIf(true) {
            it.add(defaultValue)
        }

        assertTrue(values.contains(defaultValue))
    }

    @Test
    fun alsoIf_falseCondition_callBlock() {
        val value = mutableListOf<String>()

        val result = value.alsoIf(false) {
            it.add("default-value")
        }

        assertEquals(value, result)
    }

    @Test
    fun letIf_trueCondition_callBlock() {
        val value = "value"
        val defaultValue = "default-value"

        val result = value.letIf(true) {
            defaultValue
        }

        assertEquals(defaultValue, result)
    }

    @Test
    fun letIf_falseCondition_callBlock() {
        val value = "value"

        val result = value.letIf(false) {
            "default-value"
        }

        assertNull(result)
    }

    @Test
    fun runIf_trueCondition_callBlock() {
        val value = "value"
        val defaultValue = "default-value"

        val result = value.runIf(true) {
            defaultValue
        }

        assertEquals(defaultValue, result)
    }

    @Test
    fun runIf_falseCondition_callBlock() {
        val value = "value"

        val result = value.runIf(false) {
            "default-value"
        }

        assertNull(result)
    }

    @Test
    fun getOrThrow_nullValue_throwException() {
        val value: String? = null

        assertFailsWith(Exception::class) {
            value.getOrThrow(RuntimeException())
        }
    }

    @Test
    fun getOrThrow_notNullValue_returnThis() {
        val value = "value"

        val result = value.getOrThrow(Exception())

        assertSame(value, result)
    }

    @Test
    fun getOrDefault_nullValue_returnDefault() {
        val value: String? = null
        val defaultValue = "default-value"

        val result = value.getOrDefault(defaultValue)

        assertEquals(defaultValue, result)
    }

    @Test
    fun getOrDefault_notNullValue_returnThis() {
        val value = "value"

        val result = value.getOrDefault("default-value")

        assertSame(value, result)
    }

    @Test
    fun getOrElse_nullValue_callBlock() {
        val value: String? = null
        val defaultValue = "default-value"

        val result = value.getOrElse { defaultValue }

        assertEquals(defaultValue, result)
    }

    @Test
    fun getOrElse_notNullValue_returnThis() {
        val value = "value"

        val result = value.getOrElse { throw IllegalStateException() }

        assertSame(value, result)
    }

    @Test
    fun isNull_nullValue_returnTrue() {
        val value: String? = null

        assertTrue(value.isNull())
    }

    @Test
    fun isNull_notNullValue_returnFalse() {
        val value = "value".getOrDefault(null)

        assertFalse(value.isNull())
    }

    @Test
    fun isNotNull_nullValue_returnTrue() {
        val value: String? = null.getOrDefault(null)

        assertFalse(value.isNotNull())
    }

    @Test
    fun isNotNull_notNullValue_returnFalse() {
        val value = "value".getOrDefault(null)

        assertTrue(value.isNotNull())
    }

    @Test
    fun safeAsOrNull_nullValue_returnNull() {
        val value: String? = null

        val result = value.safeAsOrNull<Int>()

        assertNull(result)
    }

    @Test
    fun safeAsOrNull_notNullValue_returnThis() {
        val value = "value"

        val result = value.safeAsOrNull<String>()

        assertSame(value, result)
    }

    @Test
    fun safeAsOrDefault_nullValue_returnDefault() {
        val value: String? = null
        val defaultValue = "default-value"

        val result = value.safeAsOrDefault(defaultValue)

        assertEquals(defaultValue, result)
    }

    @Test
    fun safeAsOrDefault_notNullValue_returnThis() {
        val value = "value"

        val result = value.safeAsOrDefault("default-value")

        assertSame(value, result)
    }

    @Test
    fun safeAsOrElse_nullValue_callBlock() {
        val value: String? = null
        val defaultValue = "default-value"

        val result = value.safeAsOrElse { defaultValue }

        assertEquals(defaultValue, result)
    }

    @Test
    fun safeAsOrElse_notNullValue_returnThis() {
        val value = "value"
        val defaultValue = "default-value"

        val result = value.safeAsOrElse {
            defaultValue
        }

        assertSame(value, result)
    }

    @Test
    fun safeAsOrThrow_nullValue_throwException() {
        val value: String? = null

        assertFailsWith(Exception::class) {
            value.safeAsOrThrow(RuntimeException())
        }
    }

    @Test
    fun safeAsOrThrow_notNullValue_returnThis() {
        val value = "value"

        val result = value.safeAsOrThrow<String>(Exception())

        assertSame(value, result)
    }

    @Test
    fun unsafeCast_nullValue_throwException() {
        val value: String? = null

        assertFailsWith(Exception::class) {
            value.unsafeCast<Int>()
        }
    }

    @Test
    fun unsafeCast_notNullValue_returnThis() {
        val value = "value"

        val result = value.unsafeCast<String>()

        assertSame(value, result)
    }

    @Test
    fun unsafeCast_notSameCast_throwException() {
        val value = "value"

        assertFailsWith(Exception::class) {
            value.unsafeCast<Int>()
        }
    }

}
