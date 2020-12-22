package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Drops the first element.
 */
public fun <T> Iterable<T>.dropFirst(): List<T> = drop(1)

public inline fun <T> Iterable<T>.dropUntil(predicate: (T) -> Boolean): List<T> {
    var yielding = false
    val list = mutableListOf<T>()

    for (item in this) {
        if (yielding) {
            list.add(item)
        } else if (!predicate(item)) {
            yielding = true
        }
    }

    return list
}

/**
 * Returns the first element matching the given [predicate], or the result of calling the [defaultValue] function if no such element is found.
 */
@ExperimentalContracts
public inline fun <T> Iterable<T>.firstOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    contract {
        callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
    }

    for (element in this) if (predicate(element)) return element

    return defaultValue()
}

public inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = mutableListOf<T>()
    for (item in this) {
        list.add(item)
        if (!predicate(item))
            break
    }

    return list
}
