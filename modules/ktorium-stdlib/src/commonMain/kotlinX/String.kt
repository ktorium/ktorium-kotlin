package org.ktorium.kotlin.stdlib

/**
 * Return `true` if the strings are equal ignoring the casing, otherwise `false`
 */
public fun String.equalsIgnoreCase(otherString: String): Boolean = equals(otherString, true)
