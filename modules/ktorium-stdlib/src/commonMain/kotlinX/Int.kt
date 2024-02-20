package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumAPI

/**
 * Return [this] if it is not null, otherwise `0`
 */
@ExperimentalKtoriumAPI
public fun Int?.orZero(): Int = this ?: 0

/**
 * Return [this] value if it is not equal to `0`, otherwise, return `null` value.
 */
@ExperimentalKtoriumAPI
public fun Int?.takeIfNotZero(): Int? = takeIf { it != 0 }
