package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Drop the last elements from the `Array` until a match against the `predicate` is `true`.
 */
public inline fun <T> Array<out T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    for (index in lastIndex downTo 0) {
        if (predicate(this[index])) {
            return take(index + 1)
        }
    }

    return emptyList()
}

/**
 * Drop the leading elements from the `Array` until a match against the `predicate` is `true`.
 */
public inline fun <T> Array<out T>.dropUntil(predicate: (T) -> Boolean): List<T> {
    for (item in this.withIndex()) {
        if (predicate(item.value)) {
            return slice(item.index until size)
        }
    }

    return emptyList()
}

/**
 * Calls the specified [block] if the array is not empty.
 */
@ExperimentalContracts
public inline fun <T, O> Array<out T>.ifNotEmpty(block: Array<out T>.() -> O?): O? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return if (isNotEmpty()) this.block() else null
}

/**
 * Take the last elements from the `Array` until a match against the `predicate` is `true`.
 */
public inline fun <T> Array<out T>.takeLastUntil(predicate: (T) -> Boolean): List<T> {
    for (index in lastIndex downTo 0) {
        if (predicate(this[index])) {
            return drop(index + 1)
        }
    }

    return toList()
}

/**
 * Take the leading elements from the `Array` until a match against the `predicate` is `true`.
 */
public inline fun <T> Array<out T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    for (item in this.withIndex()) {
        if (predicate(item.value)) {
            return take(item.index)
        }
    }

    return toList()
}
