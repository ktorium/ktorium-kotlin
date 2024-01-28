package org.ktorium.kotlin.stdlib.collections

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * Retrieves the element corresponding to [key] from this map if it already exists, otherwise,
 * creates it by calling [initializer] and saving the result to the map.
 */
@ExperimentalKtorium
public inline fun <K, E> MutableMap<K, E>.computeIfAbsent(key: K, initializer: () -> E): E {
    val value = this[key]

    return if (value == null) {
        val initial = initializer()
        this[key] = initial

        initial
    } else {
        value
    }
}
