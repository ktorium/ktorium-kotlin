package org.ktorium.kotlin.stdlib.collections

/**
 * Retrieves the element corresponding to [key] from this map if it already exists. Otherwise,
 * creates it by calling [initializer] and saving the result to the map
 */
public inline fun <K, E> MutableMap<K, E>.computeIfAbsent(key: K, initializer: () -> E): E {
    val value = this[key]
    if (value == null) {
        val initial = initializer()
        this[key] = initial
        return initial
    }

    return value
}
