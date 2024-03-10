package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Returns [this] value if it is not `null`, otherwise false.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public fun Boolean?.orFalse(): Boolean {
    contract {
        returns(true) implies (this@orFalse != null)
    }

    return this ?: false
}

/**
 * Returns [this] value if it is not `null`, otherwise true.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public fun Boolean?.orTrue(): Boolean {
    contract {
        returns(false) implies (this@orTrue != null)
    }

    return this ?: true
}

/**
 * Return `1` if [this] is `true`, otherwise return `0` value.
 */
@ExperimentalSince(Unreleased)
public fun Boolean.toInt(): Int = if (this) 1 else 0
