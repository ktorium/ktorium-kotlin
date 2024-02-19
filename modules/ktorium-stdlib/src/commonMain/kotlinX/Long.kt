package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumApi

/**
 * Return [this] if it is not null, otherwise `0`
 */
@ExperimentalKtoriumApi
public fun Long?.orZero(): Long = this ?: 0

/**
 * Return [this] value if it is not equal to `0L`, otherwise, return `null` value.
 */
@ExperimentalKtoriumApi
public fun Long?.takeIfNotZero(): Long? = takeIf { it != 0L }
