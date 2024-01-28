package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * Return `this` if it is not null, otherwise `0`
 */
@ExperimentalKtorium
public fun Double?.orZero(): Double = this ?: 0.0

/**
 * Return [this] value if it is not equal to `0.0`, otherwise, return `null` value.
 */
@ExperimentalKtorium
public fun Double?.takeIfNotZero(): Double? = takeIf { it != 0.0 }
