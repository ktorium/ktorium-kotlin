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
        returns(true) implies (this@orFalse != null)
    }

    return this ?: false
}

/**
 * Returns `this` value if it is not `null`, otherwise true.
 */
@ExperimentalContracts
public fun Boolean?.orTrue(): Boolean {
    contract {
        returns(false) implies (this@orTrue != null)
    }

    return this ?: true
}

@ExperimentalContracts
public inline fun <T> Boolean?.ifTrueOrNull(block: () -> T): T? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return (this == null || this).ifTrue { block() }
}

@ExperimentalContracts
public inline fun <T> Boolean?.ifFalseOrNull(block: () -> T): T? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return (this == null || !this).ifFalse { block() }
}

@ExperimentalContracts
public inline fun <T> Boolean.ifTrue(block: () -> T): T? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return if (this) block() else null
}

@ExperimentalContracts
public inline fun <T> Boolean.ifFalse(block: () -> T): T? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return if (this) null else block()
}
