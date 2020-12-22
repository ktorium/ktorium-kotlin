package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public fun <T> List<T>.dropLast(): List<T> = dropLast(1)

/**
 * Returns the last element matching the given [predicate], or the result of calling the [defaultValue] function if no such element is found.
 */
@ExperimentalContracts
public inline fun <T> List<T>.lastOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    contract {
        callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
    }

    for (element in this.asReversed()) if (predicate(element)) return element

    return defaultValue()
}

public inline fun <T> List<T>.takeLastUntil(predicate: (T) -> Boolean): List<T> {
    for (index in lastIndex downTo 0) {
        if (!predicate(this[index])) {
            return drop(index)
        }
    }

    return toList()
}

public inline fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    for (index in lastIndex downTo 0) {
        if (!predicate(this[index])) {
            return take(index)
        }
    }

    return emptyList()
}
