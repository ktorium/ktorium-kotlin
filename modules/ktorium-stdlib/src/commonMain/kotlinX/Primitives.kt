package org.ktorium.kotlin.stdlib

/**
 * Return `this` if it is not null, otherwise `0`
 */
public fun Byte?.orZero(): Byte = this ?: 0

/**
 * Bitwise left shift on a byte.
 *
 * @param bitCount the number of bits to shift to the left
 */
public infix fun Byte.shl(bitCount: Int): Byte = (this.toInt() shl bitCount).toByte()

/**
 * Bitwise right shift on a byte
 *
 * @param bitCount the number of bits to shift to the right
 */
public infix fun Byte.shr(bitCount: Int) : Byte = (this.toInt() shr bitCount).toByte()

/**
 * Return `this` if it is not null, otherwise `0`
 */
public fun Int?.orZero(): Int = this ?: 0

/**
 * Return `this` if it is not null, otherwise `0`
 */
public fun Long?.orZero(): Long = this ?: 0

/**
 * Return `this` if it is not null, otherwise `0`
 */
public fun Float?.orZero(): Float = this ?: 0.0f

/**
 * Return `this` if it is not null, otherwise `0`
 */
public fun Double?.orZero(): Double = this ?: 0.0
