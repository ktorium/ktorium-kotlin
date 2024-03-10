package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Calls the specified function [block] with [this] value as its receiver if [condition] is `true` and return `this` value.
 */
@ExperimentalSince(Unreleased)
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
@ExperimentalSince(Unreleased)
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
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public inline fun <T, R> T.letIf(condition: Boolean, block: (T) -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return if (condition) block(this) else null
}

/**
 * Calls the specified function [block] with [this] value as its receiver if the [condition] is `true` and returns its result, otherwise return `null` value.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public inline fun <T, R> T.runIf(condition: Boolean, block: T.() -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return if (condition) block() else null
}

/**
 * Return [this] value if [this] is not null, otherwise, throw the [cause] exception.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public fun <T> T?.getOrThrow(cause: Throwable): T {
    contract {
        returns() implies (this@getOrThrow != null)
    }

    return this ?: throw cause
}

/**
 * Return [this] value if [this] is not null otherwise, return the [default] value.
 */
@ExperimentalSince(Unreleased)
public inline fun <reified T> T?.getOrDefault(default: T): T {
    return this ?: default
}

/**
 * Return [this] value if [this] is not null, otherwise, return the result of calling the [block] function.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public inline fun <T> T?.getOrElse(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return this ?: block()
}

/**
 * Check if [this] value is null.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public fun <T> T?.isNull(): Boolean {
    contract {
        returns(true) implies (this@isNull == null)
    }

    return this == null
}

/**
 * Check if [this] value is not null.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public fun <T> T?.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
    }

    return this != null
}

/**
 * Casts the [this] value to the specified type [T] if it is not null, otherwise, return `null` value.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public inline fun <reified T> Any?.safeAsOrNull(): T? {
    contract {
        returnsNotNull() implies (this@safeAsOrNull is T)
    }

    return this as? T
}

/**
 * Casts the [this] value to the specified type [T] if it is not null, otherwise, return the [default] value.
 */
@ExperimentalSince(Unreleased)
public inline fun <reified T> Any?.safeAsOrDefault(default: T): T {
    return safeAsOrNull() ?: default
}

/**
 * Casts the [this] value to the specified type [T] if it is not null, otherwise, return the result of calling the [block] function.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public inline fun <reified T> Any?.safeAsOrElse(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return safeAsOrNull() ?: block()
}

/**
 * Casts the [this] value to the specified type [T] if it is not null, otherwise, throw the [cause] exception.
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public inline fun <reified T> Any?.safeAsOrThrow(cause: Throwable): T {
    contract {
        returns() implies (this@safeAsOrThrow is T)
    }

    return safeAsOrNull() ?: throw cause
}

/**
 * Casts the [this] value to the specified type [T].
 */
@ExperimentalSince(Unreleased)
@ExperimentalContracts
public inline fun <reified T> Any?.unsafeCast(): T {
    contract {
        returns() implies (this@unsafeCast is T)
    }

    return this as T
}
