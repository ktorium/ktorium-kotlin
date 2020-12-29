package org.ktorium.kotlin.stdlib

/**
 * Return `this` if it is not null, otherwise `0`
 */
public fun Int?.orZero(): Int = this ?: 0

/**
 * Return `this` if it is not null, otherwise `0`
 */
public fun Long?.orZero(): Long = this ?: 0
