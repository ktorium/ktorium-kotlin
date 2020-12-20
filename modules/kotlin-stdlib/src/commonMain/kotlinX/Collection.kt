package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
public inline fun <T, C : Collection<T>> C.ifNotEmpty(body: C.() -> C): C {
    contract {
        callsInPlace(body, InvocationKind.AT_MOST_ONCE)
    }

    return if (isNotEmpty()) this.body() else this
}
