package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * Return [this] if it is not null, otherwise `0`.
 */
@ExperimentalKtorium
public fun Byte?.orZero(): Byte = this ?: 0

/**
 * Bitwise left shift on a byte.
 *
 * @param bitCount the number of bits to shift to the left
 */
@ExperimentalKtorium
public infix fun Byte.shl(bitCount: Int): Byte = (this.toInt() shl bitCount).toByte()

/**
 * Bitwise right shift on a byte.
 *
 * @param bitCount the number of bits to shift to the right
 */
@ExperimentalKtorium
public infix fun Byte.shr(bitCount: Int): Byte = (this.toInt() shr bitCount).toByte()

/**
 * Return [this] value if it is not equal to `0`, otherwise, return `null` value.
 */
@ExperimentalKtorium
public fun Byte?.takeIfNotZero(): Byte? = takeIf { it != 0.toByte() }
