package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumAPI

/**
 * Return `this` if it is not null, otherwise `0`
 */
@ExperimentalKtoriumAPI
public fun Float?.orZero(): Float = this ?: 0.0f

/**
 * Return [this] value if it is not equal to `0.0f`, otherwise, return `null` value.
 */
@ExperimentalKtoriumAPI
public fun Float?.takeIfNotZero(): Float? = takeIf { it != 0.0f }
