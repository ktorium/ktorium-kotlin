package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * Return `this` if it is not null, otherwise `0`
 */
@ExperimentalKtorium
public fun Float?.orZero(): Float = this ?: 0.0f

/**
 * Return [this] value if it is not equal to `0.0f`, otherwise, return `null` value.
 */
@ExperimentalKtorium
public fun Float?.takeIfNotZero(): Float? = takeIf { it != 0.0f }
