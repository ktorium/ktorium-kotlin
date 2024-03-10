package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Checks whether a String only contains digits.
 */
@ExperimentalSince(Unreleased)
public fun CharSequence.isNumeric(): Boolean = all(Char::isDigit)

/**
 * Check if [this] is not `null` or empty string, otherwise, return `false`.
 */
@ExperimentalSince(Unreleased)
public fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return !isNullOrEmpty()
}

/**
 * Check if [this] is not `null` or consists only of whitespace characters, otherwise, return `false`.
 */
@ExperimentalContracts
@ExperimentalSince(Unreleased)
public fun CharSequence?.isNotNullOrBlank(): Boolean {
    contract {
        returns(false) implies (this@isNotNullOrBlank != null)
    }

    return (this != null) && this.isNotBlankFast()
}

/**
 * Faster version of [CharSequence.isBlank].
 *
 * Reference https://www.romainguy.dev/posts/2024/speeding-up-isblank/
 */
@ExperimentalSince(Unreleased)
public fun CharSequence.isBlankFast(): Boolean {
    for (element in this) {
        if (!element.isWhitespace()) {
            return false
        }
    }
    return true
}

@ExperimentalSince(Unreleased)
public fun CharSequence.isNotBlankFast(): Boolean = !isBlankFast()
