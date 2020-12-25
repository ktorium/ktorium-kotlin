package org.ktorium.kotlin.stdlib

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Calls the specified function [block] with `this` value as its argument if `condition` is `true` and returns this value.
 *
 * @see also
 */
@ExperimentalContracts
public inline fun <T> T.alsoIf(condition: Boolean, block: (T) -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return also {
        if (condition) {
            block(it)
        }
    }
}

/**
 * Calls the specified function [block] with `this` value as its receiver if `condition` is `true` and returns this value.
 */
@ExperimentalContracts
public inline fun <T> T.applyIf(condition: Boolean, block: T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return apply {
        if (condition) {
            block()
        }
    }
}

/**
 * Return `this` value if its not null or throw the specified exception.
 */
@ExperimentalContracts
public fun <T> T?.getOrThrow(cause: Throwable): T {
    contract {
        returns() implies (this@getOrThrow != null)
    }

    return this ?: throw cause
}

/**
 * Return `this` value if its not null or specified default value.
 */
public fun <T> T?.getOrDefault(default: T): T {
    return this ?: default
}

/**
 * Return `this` value if its not null or calls the specified default function [defaultBlock].
 */
@ExperimentalContracts
public fun <T> T?.getOrElse(defaultBlock: () -> T): T {
    contract {
        callsInPlace(defaultBlock, InvocationKind.AT_MOST_ONCE)
    }

    return this ?: defaultBlock()
}

/**
 * Calls the specified [block] if `this` is null.
 */
@ExperimentalContracts
public inline fun <T> T?.ifNull(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return this ?: block()
}

/**
 * Check if the `this` value is null.
 */
@ExperimentalContracts
public fun <T> T?.isNull(): Boolean {
    contract {
        returns(true) implies (this@isNull == null)
    }

    return this == null
}

/**
 * Check if the `this` value is not null.
 */
@ExperimentalContracts
public fun <T> T?.isNotNull(): Boolean {
    contract {
        returns(false) implies (this@isNotNull != null)
    }

    return this != null
}

/**
 * Calls the specified function [block] with this value as its argument if the `condition`is true and returns its result or null.
 */
@ExperimentalContracts
public inline fun <T, R> T.letIf(condition: Boolean, block: (T) -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return let {
        if (condition) {
            block(it)
        } else {
            null
        }
    }
}

/**
 * Calls the specified function and return its result if it is not null, else return the call to [block].
 */
@ExperimentalContracts
public inline fun <R> (() -> R?).orElse(block: () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return this.invoke() ?: block()
}

/**
 * Calls the specified function [block] with this value as its receiver if the `condition`is true and returns its result or null
 */
@ExperimentalContracts
public inline fun <T, R> T.runIf(condition: Boolean, block: T.() -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return run {
        if (condition) {
            block()
        } else {
            null
        }
    }
}

/**
 * Call the specified [block] and if it throws an exception then return null.
 */
@ExperimentalContracts
public inline fun <T> tryOrNull(crossinline block: () -> T): T? {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    return try {
        block()
    } catch (_: Throwable) {
        null
    }
}

/**
 * Call the specified [block] and silently hide any exception.
 */
@ExperimentalContracts
public inline fun trySilently(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    try {
        block()
    } catch (_: Throwable) {
        // empty
    }
}

/**
 * Calls the specified [block] and if it return `null` return the `defaultValue`.
 */
@ExperimentalContracts
public inline fun <T> tryOrDefault(defaultValue: T, crossinline block: () -> T): T = tryOrNull(block) ?: defaultValue

/**
 * Calls the specified [block] and if it returns `null`, than call the `defaultBlock`.
 */
@ExperimentalContracts
public inline fun <T> tryOrElse(defaultBlock: () -> T, crossinline block: () -> T): T = tryOrNull(block) ?: defaultBlock()

/**
 * Calls the specified function [block] with the given receiver as its receiver if the `condition` is `true` and returns its result.
 */
@ExperimentalContracts
public inline fun <T, R> withIf(receiver: T, condition: Boolean, block: T.() -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return with(receiver) {
        if (condition) {
            block()
        } else {
            null
        }
    }
}
