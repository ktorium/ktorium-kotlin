package org.ktorium.kotlin.stdlib.collections

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

/**
 * Drop the last element of the list.
 */
@ExperimentalSince(Unreleased)
public fun <T> List<T>.dropLast(): List<T> = dropLast(1)

/**
 * Drop the last elements from the `List` until a match against the `predicate` is `true`.
 */
@ExperimentalSince(Unreleased)
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
@ExperimentalSince(Unreleased)
public inline fun <T> List<T>.takeLastUntil(predicate: (T) -> Boolean): List<T> {
    val iterator = listIterator(size)

    while (iterator.hasPrevious()) {
        if (predicate(iterator.previous())) {
            return drop(iterator.nextIndex() + 1)
        }
    }

    return toList()
}

@ExperimentalSince(Unreleased)
public fun <T> List<T>.forEachIndexedReversed(action: (index: Int, T) -> Unit) {
    for (i in lastIndex..0) {
        action.invoke(i, this.elementAt(i))
    }
}
