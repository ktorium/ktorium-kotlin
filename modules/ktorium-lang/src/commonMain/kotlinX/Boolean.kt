package org.ktorium.sdk.lang

/**
 * Returns `this` value if it is not `null`, otherwise false.
 */
public fun Boolean?.orFalse(): Boolean = this ?: false

/**
 * Returns `this` value if it is not `null`, otherwise true.
 */
public fun Boolean?.orTrue(): Boolean = this ?: true

/**
 * Will execute [block] if this [Boolean] is true.
 *
 * @return This boolean
 */
public inline fun Boolean.ifTrue(block: () -> Unit): Boolean {
    if (this) block()
    return this
}

/**
 * Will execute [block] if this [Boolean] is false.
 *
 * @return This boolean
 */
public inline fun Boolean.ifFalse(block: () -> Unit): Boolean {
    if (!this) block()
    return this
}

public fun Boolean.toInt(): Int = if(this) 1 else 0
