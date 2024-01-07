package org.ktorium.kotlin.stdlib

/**
 * Drops the first element.
 */
public fun <T> Array<out T>.dropFirst(): List<T> = drop(1)

/**
 * Drop the last element of the list.
 */
public fun <T> Array<out T>.dropLast(): List<T> = dropLast(1)

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
    var yielding = false
    val list = mutableListOf<T>()
    for (item in this) {
        if (yielding) {
            list.add(item)
        } else if (predicate(item)) {
            list.add(item)
            yielding = true
        }
    }

    return list
}

/**
 * Returns the first element matching the given [predicate] or the result of the [defaultValue] if no such element is found.
 */
public inline fun <T> Array<out T>.firstOrDefault(predicate: (T) -> Boolean, defaultValue: T): T {
    return firstOrNull(predicate) ?: defaultValue
}

/**
 * Returns the first element matching the given [predicate] or the result of calling the [block] function if no such element is found.
 */
public inline fun <T> Array<out T>.firstOrElse(predicate: (T) -> Boolean, block: () -> T): T {
    return firstOrNull(predicate) ?: block()
}

/**
 * Returns the last element matching the given [predicate], or the [defaultValue] if no such element is found.
 */
public inline fun <T> Array<out T>.lastOrDefault(predicate: (T) -> Boolean, defaultValue: T): T {
    return lastOrNull(predicate) ?: defaultValue
}

/**
 * Returns the last element matching the given [predicate], or the result of calling the [block] function if no such element is found.
 */
public inline fun <T> Array<out T>.lastOrElse(predicate: (T) -> Boolean, block: () -> T): T {
    return lastOrNull(predicate) ?: block()
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

public inline fun <T> Array<out T>.ifNotEmpty(body: Array<out T>.() -> T?): T? =
    if (isNotEmpty()) body() else null
