package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumAPI

/**
 * Check if [this] string is equal to [other] string, ignoring the casing, otherwise `false`
 */
@ExperimentalKtoriumAPI
public fun String.equalsIgnoreCase(other: String): Boolean = equals(other, true)

/**
 * Compare this string with [other] string, ignoring the casing.
 */
@ExperimentalKtoriumAPI
public fun String.compareToIgnoreCase(other: String): Int = compareTo(other, true)
