package org.ktorium.kotlin.stdlib.collections

/**
 * Drop the last element of the list.
 */
public fun <T> List<T>.dropLast(): List<T> = dropLast(1)

/**
 * Drop the last elements from the `List` until a match against the `predicate` is `true`.
 */
public inline fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    if (isNotEmpty()) {
        val iterator = listIterator(size)
        while (iterator.hasPrevious()) {
            if (predicate(iterator.previous())) {
                return take(iterator.nextIndex() + 1)
            }
        }
    }

    return emptyList()
}

/**
 * Returns the last element matching the given [predicate], or the [defaultValue] if no such element is found.
 */
public inline fun <T> List<T>.lastOrDefault(predicate: (T) -> Boolean, defaultValue: T): T {
    return lastOrNull(predicate) ?: defaultValue
}

/**
 * Returns the last element matching the given [predicate], or the result of calling the [defaultValue] function if no such element is found.
 */
public inline fun <T> List<T>.lastOrElse(predicate: (T) -> Boolean, defaultValue: () -> T): T {
    return lastOrNull(predicate) ?: defaultValue()
}

/**
 * Take the last elements from the `List` until a match against the `predicate` is `true`.
 */
public inline fun <T> List<T>.takeLastUntil(predicate: (T) -> Boolean): List<T> {
    if (isNotEmpty()) {
        val iterator = listIterator(size)
        while (iterator.hasPrevious()) {
            if (predicate(iterator.previous())) {
                return drop(iterator.nextIndex() + 1)
            }
        }
    }
    return toList()
}

/**
 * Take the leading elements until a match against the `predicate` is `true`.
 */
public inline fun <T> List<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = mutableListOf<T>()
    for (item in this) {
        if (predicate(item)) {
            break
        }
        list.add(item)
    }
    return list
}
