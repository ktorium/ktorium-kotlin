package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * Return [this] if it is not null, otherwise `0`
 */
@ExperimentalKtorium
public fun Int?.orZero(): Int = this ?: 0

/**
 * Return [this] value if it is not equal to `0`, otherwise, return `null` value.
 */
@ExperimentalKtorium
public fun Int?.takeIfNotZero(): Int? = takeIf { it != 0 }
