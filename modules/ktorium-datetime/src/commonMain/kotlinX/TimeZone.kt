@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.TimeZone
import org.ktorium.kotlin.ExperimentalKtorium

@ExperimentalKtorium
public fun TimeZone.Companion.ofOrNull(id: String): TimeZone? = runCatching { of(id) }.getOrNull()
