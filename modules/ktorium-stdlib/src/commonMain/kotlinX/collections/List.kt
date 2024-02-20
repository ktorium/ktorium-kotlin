package org.ktorium.kotlin.stdlib.collections

import org.ktorium.kotlin.ExperimentalKtoriumAPI

/**
 * Drop the last element of the list.
 */
@ExperimentalKtoriumAPI
public fun <T> List<T>.dropLast(): List<T> = dropLast(1)

/**
 * Drop the last elements from the `List` until a match against the `predicate` is `true`.
 */
@ExperimentalKtoriumAPI
public inline fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    val iterator = listIterator(size)

    while (iterator.hasPrevious()) {
        if (predicate(iterator.previous())) {
            return take(iterator.nextIndex() + 1)
        }
    }

    return emptyList()
}

/**
 * Take the last elements from the `List` until a match against the [predicate] is `true`.
 */
@ExperimentalKtoriumAPI
public inline fun <T> List<T>.takeLastUntil(predicate: (T) -> Boolean): List<T> {
    val iterator = listIterator(size)

    while (iterator.hasPrevious()) {
        if (predicate(iterator.previous())) {
            return drop(iterator.nextIndex() + 1)
        }
    }

    return toList()
}
