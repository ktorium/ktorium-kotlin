package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumAPI

/**
 * Return [this] if it is not null, otherwise `0`
 */
@ExperimentalKtoriumAPI
public fun Long?.orZero(): Long = this ?: 0

/**
 * Return [this] value if it is not equal to `0L`, otherwise, return `null` value.
 */
@ExperimentalKtoriumAPI
public fun Long?.takeIfNotZero(): Long? = takeIf { it != 0L }
