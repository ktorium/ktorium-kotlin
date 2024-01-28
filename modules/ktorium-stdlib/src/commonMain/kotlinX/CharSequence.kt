package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * Checks whether a String only contains digits.
 */
@ExperimentalKtorium
public fun CharSequence.isNumeric(): Boolean = all(Char::isDigit)

/**
 * Check if [this] is not `null` or empty string, otherwise, return `false`.
 */
@ExperimentalKtorium
public fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return !isNullOrEmpty()
}

/**
 * Check if [this] is not `null` or consists only of whitespace characters, otherwise, return `false`.
 */
@ExperimentalKtorium
public fun CharSequence?.isNotNullOrBlank(): Boolean {
    return !isNullOrBlank()
}
