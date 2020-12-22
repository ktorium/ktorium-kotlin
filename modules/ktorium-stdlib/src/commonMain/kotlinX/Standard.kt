package org.ktorium.kotlin.stdlib

public inline fun <T> T.alsoIf(condition: Boolean, block: (T) -> T): T = also {
    if (condition) {
        block(it)
    }
}

public inline fun <T> T.applyIf(condition: Boolean, block: T.() -> T): T = apply {
    if (condition) {
        block()
    }
}

public fun <T> T?.getOrThrow(cause: Throwable): T {
    return this ?: throw cause
}

public fun <T> T?.getOrDefault(default: T): T {
    return this ?: default
}

public fun <T> T?.getOrElse(defaultBlock: () -> T): T {
    return this ?: defaultBlock()
}

public fun <T> T?.isNull(): Boolean = this == null
public fun <T> T?.isNotNull(): Boolean = this != null

public inline fun <T, R> T.letIf(condition: Boolean, block: (T) -> R): R? = let {
    if (condition) {
        block(it)
    } else {
        null
    }
}

public inline fun <R> (() -> R?).orElse(block: () -> R): R = this.invoke() ?: block()

public inline fun <T, R> T.runIf(condition: Boolean, block: T.() -> R): R? = run {
    if (condition) {
        block()
    } else {
        null
    }
}

public inline fun <T, R> withIf(receiver: T, condition: Boolean, block: T.() -> R): R? = with(receiver) {
    if (condition) {
        block()
    } else {
        null
    }
}

public inline fun <T> tryOrNull(block: () -> T): T? = try {
    block()
} catch (e: Exception) {
    null
}

public inline fun trySilently(block: () -> Unit): Unit = try {
    block()
} catch (e: Exception) {
}

public inline fun <T> tryOrDefault(defaultValue: T, block: () -> T): T = tryOrNull(block) ?: defaultValue

public inline fun <T> tryOrElse(defaultValue: () -> T, block: () -> T): T = tryOrNull(block) ?: defaultValue()

/**
 * Calls the specified function [block] with `value` as its argument if `value` is not null
 *
 * val value: String? = ...
 * withNotNull(value) {
 *    ...do something
 * }
 * */
public inline fun <A> withNotNull(value: A?, block: A.() -> Unit) {
    value?.let(block)
}
