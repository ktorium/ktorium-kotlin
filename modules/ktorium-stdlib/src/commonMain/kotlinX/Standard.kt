package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Calls the specified function [block] with [this] value as its receiver if [condition] is `true` and return `this` value.
 */
@ExperimentalContracts
public inline fun <T> T.applyIf(condition: Boolean, block: T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    if (condition) {
        block()
    }

    return this
}

/**
 * Calls the specified function [block] with [this] value as its argument if [condition] is `true` and returns `this` value.
 */
@ExperimentalContracts
public inline fun <T> T.alsoIf(condition: Boolean, block: (T) -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    if (condition) {
        block(this)
    }

    return this
}

/**
 * Calls the specified function [block] with [this] value as its argument if the [condition] is `true` and returns its result, otherwise return `null` value.
 */
@ExperimentalContracts
public inline fun <T, R> T.letIf(condition: Boolean, block: (T) -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        returns() implies (this@letIf != null)
    }

    return if (condition) block(this) else null
}

/**
 * Calls the specified function [block] if the [condition] is `true` and returns its result, otherwise return `null` value.
 */
@ExperimentalContracts
public inline fun <T, R> T.runIf(condition: Boolean, block: T.() -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        returns() implies (this@runIf != null)
    }

    return if (condition) block() else null
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
@ExperimentalContracts
public inline fun <reified T> T?.getOrDefault(default: T): T {
    contract {
        returns() implies (this@getOrDefault is T)
    }

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

@ExperimentalContracts
public inline fun <reified T> Any?.safeAsOrNull(): T? {
    contract {
        returnsNotNull() implies (this@safeAsOrNull is T)
    }

    return this as? T
}

@ExperimentalContracts
public inline fun <reified T> Any?.safeAsOrDefault(default: T): T {
    contract {
        returnsNotNull() implies (this@safeAsOrDefault is T)
    }

    return safeAsOrNull() ?: default
}

@ExperimentalContracts
public inline fun <reified T> Any?.safeAsOrElse(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return safeAsOrNull() ?: block()
}

@ExperimentalContracts
public inline fun <reified T> Any?.safeAsOrThrow(cause: Throwable): T {
    contract {
        returns() implies (this@safeAsOrThrow is T)
    }

    return safeAsOrNull() ?: throw cause
}

@ExperimentalContracts
public inline fun <reified T> Any?.unsafeCast(): T {
    contract {
        returns() implies (this@unsafeCast is T)
    }

    return this as T
}
