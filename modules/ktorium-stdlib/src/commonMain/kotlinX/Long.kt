package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * Return [this] if it is not null, otherwise `0`
 */
@ExperimentalKtorium
public fun Long?.orZero(): Long = this ?: 0

/**
 * Return [this] value if it is not equal to `0L`, otherwise, return `null` value.
 */
@ExperimentalKtorium
public fun Long?.takeIfNotZero(): Long? = takeIf { it != 0L }
