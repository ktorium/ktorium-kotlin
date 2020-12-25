package org.ktorium.kotlin.stdlib

/**
 * Drop the last element of the list.
 */
public fun <T> List<T>.dropLast(): List<T> = dropLast(1)

/**
 * Drop the last elements from the `List` until a match against the `predicate` is `true`.
 */
public inline fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    for (index in lastIndex downTo 0) {
        if (!predicate(this[index])) {
            return take(index)
        }
    }

    return emptyList()
}

/**
 * Returns the last element matching the given [predicate], or the result of calling the [defaultValue] function if no such element is found.
 */
public inline fun <T> List<T>.lastOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    for (element in this.asReversed()) if (predicate(element)) return element

    return defaultValue()
}

/**
 * Take the last elements from the `List` until a match against the `predicate` is `true`.
 */
public inline fun <T> List<T>.takeLastUntil(predicate: (T) -> Boolean): List<T> {
    for (index in lastIndex downTo 0) {
        if (predicate(this[index])) {
            return drop(index + 1)
        }
    }

    return toList()
}
