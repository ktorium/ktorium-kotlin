package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumAPI

/**
 * Return `this` if it is not null, otherwise `0`
 */
@ExperimentalKtoriumAPI
public fun Double?.orZero(): Double = this ?: 0.0

/**
 * Return [this] value if it is not equal to `0.0`, otherwise, return `null` value.
 */
@ExperimentalKtoriumAPI
public fun Double?.takeIfNotZero(): Double? = takeIf { it != 0.0 }
