package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumApi

/**
 * Check if [this] string is equal to [other] string, ignoring the casing, otherwise `false`
 */
@ExperimentalKtoriumApi
public fun String.equalsIgnoreCase(other: String): Boolean = equals(other, true)

/**
 * Compare this string with [other] string, ignoring the casing.
 */
@ExperimentalKtoriumApi
public fun String.compareToIgnoreCase(other: String): Int = compareTo(other, true)
