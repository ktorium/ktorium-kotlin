package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Returns `this` value if it is not `null`, otherwise false.
 */
@ExperimentalContracts
public fun Boolean?.orFalse(): Boolean {
    contract {
        returns(false) implies (this@orFalse == null)
    }

    return this ?: false
}

/**
 * Returns `this` value if it is not `null`, otherwise true.
 */
@ExperimentalContracts
public fun Boolean?.orTrue(): Boolean {
    contract {
        returns(true) implies (this@orTrue == null)
    }

    return this ?: true
}

/**
 * Will execute [block] if this [Boolean] is true.
 *
 * @return This boolean
 */
@ExperimentalContracts
public inline fun Boolean.ifTrue(block: () -> Unit): Boolean {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    if (this) block()
    return this
}

/**
 * Will execute [block] if this [Boolean] is false.
 *
 * @return This boolean
 */
@ExperimentalContracts
public inline fun Boolean.ifFalse(block: () -> Unit): Boolean {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    if (!this) block()
    return this
}

public fun Boolean.toInt(): Int = if(this) 1 else 0
