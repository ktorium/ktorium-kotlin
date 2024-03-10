package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

/**
 * Return `this` if it is not null, otherwise `0`
 */
@ExperimentalSince(Unreleased)
public fun Float?.orZero(): Float = this ?: 0.0f

/**
 * Return [this] value if it is not equal to `0.0f`, otherwise, return `null` value.
 */
@ExperimentalSince(Unreleased)
public fun Float?.takeIfNotZero(): Float? = takeIf { it != 0.0f }
