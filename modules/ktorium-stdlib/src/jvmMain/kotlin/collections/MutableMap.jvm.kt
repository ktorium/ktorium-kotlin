@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.stdlib.collections

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

@ExperimentalSince(Unreleased)
public actual inline fun <K, V> MutableMap<K, V>.merge(
    key: K,
    value: V & Any,
    crossinline mappingFunction: (V & Any, V & Any) -> V?
): V? =
    merge(key, value) { k, v -> mappingFunction(k, v) }

@ExperimentalSince(Unreleased)
public actual inline fun <K, V> MutableMap<K, V>.compute(key: K, crossinline mappingFunction: (K, V?) -> V?): V? =
    compute(key) { k, v -> mappingFunction(k, v) }

@ExperimentalSince(Unreleased)
public actual inline fun <K, V> MutableMap<K, V>.computeIfAbsent(key: K, crossinline mappingFunction: (K) -> V): V =
    computeIfAbsent(key) { k -> mappingFunction(k) }

@ExperimentalSince(Unreleased)
public actual inline fun <K, V> MutableMap<K, V>.computeIfPresent(key: K, crossinline mappingFunction: (K, V & Any) -> V?): V? =
    computeIfPresent(key) { k, v -> mappingFunction(k, v) }
