package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
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
