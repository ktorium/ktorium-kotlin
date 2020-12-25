package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Drops the first element.
 */
public fun <T> Iterable<T>.dropFirst(): List<T> = drop(1)

/**
 * Drop the leading elements from the `Iterable` until a match against the `predicate` is `true`.
 */
public inline fun <T> Iterable<T>.dropUntil(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    var yielding = false

    for (item in this) {
        if (yielding) {
            result.add(item)
        } else if (predicate(item)) {
            yielding = true
            result.add(item)
        }
    }

    return result
}

/**
 * Returns the first element matching the given [predicate] or the result of the [defaultValue] if no such element is found.
 */
public inline fun <T> Iterable<T>.firstOrDefault(defaultValue: T, predicate: (T) -> Boolean): T {
    for (element in this) if (predicate(element)) return element

    return defaultValue
}

/**
 * Returns the first element matching the given [predicate] or the result of calling the [defaultValue] function if no such element is found.
 */
@ExperimentalContracts
public inline fun <T> Iterable<T>.firstOrElse(defaultValue: () -> T, predicate: (T) -> Boolean): T {
    contract {
        callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
    }

    for (element in this) if (predicate(element)) return element

    return defaultValue()
}

/**
 * Take the leading elements from the `Array` until a match against the `predicate` is `true`.
 */
public inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()

    for (item in this) {
        if (predicate(item)) {
            break
        }

        result.add(item)
    }

    return result
}
