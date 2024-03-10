package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

/**
 * Check if [this] string is equal to [other] string, ignoring the casing, otherwise `false`
 */
@ExperimentalSince(Unreleased)
public fun String.equalsIgnoreCase(other: String): Boolean = equals(other, true)

/**
 * Compare this string with [other] string, ignoring the casing.
 */
@ExperimentalSince(Unreleased)
public fun String.compareToIgnoreCase(other: String): Int = compareTo(other, true)
