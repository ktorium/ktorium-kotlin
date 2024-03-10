package org.ktorium.kotlin.stdlib.collections

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

/**
 * Get a list containing all elements except first element.
 */
@ExperimentalSince(Unreleased)
public fun <T> Iterable<T>.dropFirst(): List<T> = drop(1)

/**
 * Get a list without the leading elements from [this] until a match against the [predicate] is `true`.
 */
@ExperimentalSince(Unreleased)
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
 * Get the first element matching the given [predicate], otherwise, return [defaultValue] if no such element is found.
 */
@ExperimentalSince(Unreleased)
public inline fun <T> Iterable<T>.firstOrDefault(predicate: (T) -> Boolean, defaultValue: T): T = firstOrNull(predicate) ?: defaultValue

/**
 * Get the first element matching the given [predicate], otherwise, return the result of calling the [block] function if no such element is found.
 */
@ExperimentalSince(Unreleased)
public inline fun <T> Iterable<T>.firstOrElse(predicate: (T) -> Boolean, block: () -> T): T = firstOrNull(predicate) ?: block()

/**
 * Get the last element matching the given [predicate], otherwise, return the [defaultValue] if no such element is found.
 */
@ExperimentalSince(Unreleased)
public inline fun <T> Iterable<T>.lastOrDefault(predicate: (T) -> Boolean, defaultValue: T): T = lastOrNull(predicate) ?: defaultValue

/**
 * Get the last element matching the given [predicate], otherwise, return the result of calling the [block] function if no such element is found.
 */
@ExperimentalSince(Unreleased)
public inline fun <T> Iterable<T>.lastOrElse(predicate: (T) -> Boolean, block: () -> T): T = lastOrNull(predicate) ?: block()

/**
 * Take the leading elements until a match against the [predicate] is `true`.
 */
@ExperimentalSince(Unreleased)
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

/**
 * Returns the index of the first element matching the given predicate, or `null` if the element is not found.
 */
@ExperimentalSince(Unreleased)
public inline fun <T> Iterable<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? = indexOfFirst(predicate).takeIf { it != -1 }

/**
 * Returns the index of the last element matching the given predicate, or `null` if the element is not found.
 */
@ExperimentalSince(Unreleased)
public inline fun <T> Iterable<T>.indexOfLastOrNull(predicate: (T) -> Boolean): Int? = indexOfLast(predicate).takeIf { it != -1 }

/**
 * Appends all non-null elements matching the given [predicate].
 */
@ExperimentalSince(Unreleased)
public inline fun <T> Iterable<T>.filterNotNullBy(predicate: (T) -> Boolean): Collection<T> =
    filterNotNullTo(mutableListOf(), predicate)

/**
 * Appends all non-null elements matching the given [predicate] to the given [destination].
 */
@ExperimentalSince(Unreleased)
public inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterNotNullTo(destination: C, predicate: (T) -> Boolean): C {
    for (element in this) {
        if ((element != null) && predicate(element)) {
            destination.add(element)
        }
    }
    return destination
}
