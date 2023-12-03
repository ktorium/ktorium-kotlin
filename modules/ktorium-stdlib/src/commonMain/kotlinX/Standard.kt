package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Calls the specified function [block] with [receiver] value as its receiver if the [condition] is `true` and returns this result, otherwise return `null` value.
 */
public inline fun <T, R> withIf(condition: Boolean, receiver: T, block: T.() -> R): R? = with(receiver) {
    if (condition) block() else null
}

/**
 * Calls the specified function [block] with [this] value as its receiver if [condition] is `true` and return `this` value.
 */
public inline fun <T> T.applyIf(condition: Boolean, block: T.() -> Unit): T = apply {
    if (condition) {
        block()
    }
}

/**
 * Calls the specified function [block] with [this] value as its argument if [condition] is `true` and returns `this` value.
 */
public inline fun <T> T.alsoIf(condition: Boolean, block: (T) -> Unit): T = also {
    if (condition) {
        block(it)
    }
}

/**
 * Calls the specified function [block] with [this] value as its argument if the [condition] is `true` and returns its result, otherwise return `null` value.
 */
public inline fun <T, R> T.letIf(condition: Boolean, block: (T) -> R): R? = let {
    if (condition) block(it) else null
}

public inline fun <T, R> T.runIf(condition: Boolean, block: T.() -> R): R? = run {
    if (condition) {
        block()
    } else {
        null
    }
}

/**
 * Return [this] value if it's not null or throw the specified exception.
 */
@ExperimentalContracts
public fun <T> T?.getOrThrow(cause: Throwable): T {
    contract {
        returns() implies (this@getOrThrow != null)
    }

    return this ?: throw cause
}

/**
 * Return [this] value if it's not null or [default] value.
 */
public fun <T> T?.getOrDefault(default: T): T {
    return this ?: default
}

/**
 * Return [this] value if it's not null or calls the specified default function [block].
 */
@ExperimentalContracts
public inline fun <T> T?.getOrElse(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return this ?: block()
}

/**
 * Check if the [this] value is null.
 */
@ExperimentalContracts
public fun <T> T?.isNull(): Boolean {
    contract {
        returns(true) implies (this@isNull == null)
    }

    return this == null
}

/**
 * Check if the [this] value is not null.
 */
@ExperimentalContracts
public fun <T> T?.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
    }

    return this != null
}

public inline fun <reified T> Any?.safeAsOrNull(): T? = this as? T
public inline fun <reified T> Any?.safeAsOrDefault(default: T): T = safeAsOrNull() ?: default
public inline fun <reified T> Any?.safeAsOrElse(block: () -> T): T = safeAsOrNull() ?: block()
public inline fun <reified T> Any?.safeAsOrThrow(cause: Throwable): T = safeAsOrNull() ?: throw cause
public inline fun <reified T> Any?.unsafeCast(): T = this as T
