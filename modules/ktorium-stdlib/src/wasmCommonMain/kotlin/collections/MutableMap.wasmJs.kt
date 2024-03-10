@file:Suppress("PackageDirectoryMismatch")
@file:OptIn(InternalKtoriumAPI::class)

package org.ktorium.kotlin.stdlib.collections

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.InternalKtoriumAPI
import org.ktorium.kotlin.KtoriumVersion.Unreleased

@ExperimentalSince(Unreleased)
public actual inline fun <K, V> MutableMap<K, V>.merge(
    key: K,
    value: V & Any,
    mappingFunction: (V & Any, V & Any) -> V?
): V? = internalMergeImpl(key, value, mappingFunction)

@ExperimentalSince(Unreleased)
public actual inline fun <K, V> MutableMap<K, V>.compute(key: K, crossinline mappingFunction: (K, V?) -> V?): V? =
    internalComputeImpl(key, mappingFunction)

@ExperimentalSince(Unreleased)
public actual inline fun <K, V> MutableMap<K, V>.computeIfAbsent(key: K, crossinline mappingFunction: (K) -> V): V =
    internalComputeIfAbsentImpl(key, mappingFunction)

@ExperimentalSince(Unreleased)
public actual inline fun <K, V> MutableMap<K, V>.computeIfPresent(key: K, crossinline mappingFunction: (K, V & Any) -> V?): V? =
    internalComputeIfPresentImpl(key, mappingFunction)
