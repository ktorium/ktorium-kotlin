package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
public inline fun <T : Any> T?.ifNull(defaultValue: () -> T): T {
    contract {
        callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
    }

    return this ?: defaultValue()
}

public inline fun <reified T> Any?.safeAs(): T? = this as? T

public inline fun <reified T> Any?.cast(): T = this as T

public inline fun <reified T> Any?.safeAsOrElse(defaultValue: () -> T): T = safeAs() ?: defaultValue()
