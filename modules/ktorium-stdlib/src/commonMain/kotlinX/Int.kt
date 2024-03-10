package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

/**
 * Return [this] if it is not null, otherwise `0`
 */
@ExperimentalSince(Unreleased)
public fun Int?.orZero(): Int = this ?: 0

/**
 * Return [this] value if it is not equal to `0`, otherwise, return `null` value.
 */
@ExperimentalSince(Unreleased)
public fun Int?.takeIfNotZero(): Int? = takeIf { it != 0 }
