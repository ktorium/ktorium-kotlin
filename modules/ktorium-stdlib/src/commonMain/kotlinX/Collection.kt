package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
public inline fun <T, C : Collection<T>, O> C.ifNotEmpty(body: C.() -> O?): O? {
    contract {
        callsInPlace(body, InvocationKind.AT_MOST_ONCE)
    }

    return if (isNotEmpty()) this.body() else null
}
