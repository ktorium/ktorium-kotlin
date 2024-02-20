package org.ktorium.kotlin.stdlib.collections

import org.ktorium.kotlin.ExperimentalKtoriumAPI
import org.ktorium.kotlin.InternalKtoriumAPI

/**
 * Provide [key] and [value], and if the value corresponding to [key] does not exist in `map`, this key-value pair will be stored.
 * If there is a record that conflicts with [key], then use the [remapping] function to calculate the new value from the current value and the old value.
 * Store the new value when the calculated new value is not `null`, otherwise remove the old value.
 */
@ExperimentalKtoriumAPI
public expect inline fun <K, V> MutableMap<K, V>.merge(
    key: K,
    value: V & Any,
    crossinline mappingFunction: (V & Any, V & Any) -> V?
): V?

/**
 * Provide [key] and compute from `map` via [remapping].
 * Where [K] of [remapping] is [key], [V] is the value that already exists in `map` that matches [key], if not, it is `null`.
 * When the evaluation result of [remapping] is not `null`, insert this value and return it, otherwise delete the original value (if any) and return `null`.
 */
@ExperimentalKtoriumAPI
public expect inline fun <K, V> MutableMap<K, V>.compute(key: K, crossinline mappingFunction: (K, V?) -> V?): V?

/**
 * Retrieves the element corresponding to [key] from this map if it already exists, otherwise,
 * creates it by calling [remapping] and saving the result to the map.
 */
@ExperimentalKtoriumAPI
public expect inline fun <K, V> MutableMap<K, V>.computeIfAbsent(key: K, crossinline mappingFunction: (K) -> V): V

/**
 * Provides [key] to retrieve the matching value from the `map`, if there is a matching value,
 * Then the calculated value is returned after calculation and storage through [mappingFunction], otherwise `null` is returned directly.
 * If [mappingFunction] evaluates to `null`, the original value will be removed and `null` will be returned.
 */
@ExperimentalKtoriumAPI
public expect inline fun <K, V> MutableMap<K, V>.computeIfPresent(key: K, crossinline mappingFunction: (K, V & Any) -> V?): V?

@InternalKtoriumAPI
@PublishedApi
internal inline fun <K, V> MutableMap<K, V>.internalMergeImpl(
    key: K,
    value: V & Any,
    mappingFunction: (V & Any, V & Any) -> V?
): V? {
    val old = get(key)
    val newValue = if (old == null) value else mappingFunction(old, value)

    if (newValue == null) {
        if (old != null) {
            remove(key)
        }
    } else {
        put(key, newValue)
    }

    return newValue
}

@InternalKtoriumAPI
@PublishedApi
internal inline fun <K, V> MutableMap<K, V>.internalComputeImpl(key: K, mappingFunction: (K, V?) -> V?): V? {
    val oldValue = get(key)

    val newValue = mappingFunction(key, oldValue)

    if (newValue == null) {
        if (oldValue != null || containsKey(key)) {
            remove(key)
            return null
        } else {
            return null
        }
    } else {
        put(key, newValue)
        return newValue
    }
}

@InternalKtoriumAPI
@PublishedApi
internal inline fun <K, V> MutableMap<K, V>.internalComputeIfAbsentImpl(key: K, mappingFunction: (K) -> V): V {
    val value = get(key)

    if (value == null) {
        val newValue = mappingFunction(key)
        put(key, newValue)
        return newValue
    }

    return value
}

@InternalKtoriumAPI
@PublishedApi
internal inline fun <K, V> MutableMap<K, V>.internalComputeIfPresentImpl(key: K, mappingFunction: (K, V & Any) -> V?): V? {
    val old = get(key)
    if (old != null) {
        val newValue = mappingFunction(key, old)
        if (newValue != null) {
            put(key, newValue)
            return newValue
        } else {
            remove(key)
            return null
        }
    }

    return null
}
