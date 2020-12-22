package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public inline fun <T> Array<out T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    for (index in lastIndex downTo 0) {
        if (!predicate(this[index])) {
            return take(index)
        }
    }

    return emptyList()
}

public inline fun <T> Array<out T>.dropUntil(predicate: (T) -> Boolean): List<T> {
    var yielding = false
    val list = mutableListOf<T>()
    for (item in this)
        if (yielding)
            list.add(item)
        else if (!predicate(item)) {
            yielding = true
        }

    return list
}

@ExperimentalContracts
public inline fun <T, O> Array<out T>.ifNotEmpty(body: Array<out T>.() -> O?): O? {
    contract {
        callsInPlace(body, InvocationKind.AT_MOST_ONCE)
    }

    return if (isNotEmpty()) this.body() else null
}

public inline fun <T> Array<out T>.takeLastUntil(predicate: (T) -> Boolean): List<T> {
    for (index in lastIndex downTo 0) {
        if (!predicate(this[index])) {
            return drop(index)
        }
    }
    return toList()
}

public inline fun <T> Array<out T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = mutableListOf<T>()
    for (item in this) {
        list.add(item)
        if (!predicate(item))
            break
    }
    return list
}
