package org.ktorium.kotlin.stdlib

import kotlin.contracts.contract

/**
 * Checks whether a String is numeric (meaning it contains an Integer)
 */
public fun CharSequence.isNumeric(): Boolean = all(Char::isDigit)

/**
 * Returns `false` if this nullable char sequence is either `null` or empty.
 *
 * @see isNullOrEmpty
 */
public fun CharSequence?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }

    return !isNullOrEmpty()
}

/**
 * Returns `false` if this nullable char sequence is either `null` or empty or consists solely of whitespace characters.
 */
public fun CharSequence?.isNotNullOrBlank(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrBlank != null)
    }

    return !isNullOrBlank()
}
