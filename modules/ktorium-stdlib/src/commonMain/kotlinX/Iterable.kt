package org.ktorium.kotlin.stdlib

/**
 * Drops the first element.
 */
public fun <T> Iterable<T>.dropFirst(): List<T> = drop(1)

/**
 * Drop the leading elements from the `Iterable` until a match against the `predicate` is `true`.
 */
public inline fun <T> Iterable<T>.dropUntil(predicate: (T) -> Boolean): List<T> {
    val result = arrayListOf<T>()
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
 * Returns the first element matching the given [predicate] or the [defaultValue] if no such element is found.
 */
public inline fun <T> Iterable<T>.firstOrDefault(predicate: (T) -> Boolean, defaultValue: T): T {
    return firstOrNull(predicate) ?: defaultValue
}

/**
 * Returns the first element matching the given [predicate] or the result of calling the [defaultValue] function if no such element is found.
 */
public inline fun <T> Iterable<T>.firstOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    return firstOrNull(predicate) ?: defaultValue()
}

/**
 * Returns the last element matching the given [predicate], or the [defaultValue] if no such element is found.
 */
public inline fun <T> Iterable<T>.lastOrDefault(predicate: (T) -> Boolean, defaultValue: T): T {
    return lastOrNull(predicate) ?: defaultValue
}

/**
 * Returns the last element matching the given [predicate], or the result of calling the [defaultValue] function if no such element is found.
 */
public inline fun <T> Iterable<T>.lastOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    return lastOrNull(predicate) ?: defaultValue()
}

/**
 * Take the leading elements until a match against the `predicate` is `true`.
 */
public inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = arrayListOf<T>()
    for (item in this) {
        if (predicate(item)) {
            break
        }
        list.add(item)
    }
    return list
}
