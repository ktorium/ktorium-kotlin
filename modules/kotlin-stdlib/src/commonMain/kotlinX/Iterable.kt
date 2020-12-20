package org.ktorium.kotlin.stdlib

/**
 * Returns the first element matching the given [predicate], or the result of calling the [defaultValue] function if no such element is found.
 */
public inline fun <T> Iterable<T>.firstOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    for (element in this) if (predicate(element)) return element

    return defaultValue()
}

/**
 * Returns the last element matching the given [predicate], or the result of calling the [defaultValue] function if no such element is found.
 */
public inline fun <T> Iterable<T>.lastOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    var last: T? = null
    var found = false

    for (element in this) {
        if (predicate(element)) {
            last = element
            found = true
        }
    }

    if (!found) {
        return defaultValue()
    }

    @Suppress("UNCHECKED_CAST")
    return last as T
}

public fun <T> Iterable<T>.dropFirst(): List<T> = drop(1)
