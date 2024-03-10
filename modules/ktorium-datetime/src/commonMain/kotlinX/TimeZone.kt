@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.TimeZone
import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

@ExperimentalSince(Unreleased)
public fun TimeZone.Companion.ofOrNull(id: String): TimeZone? = runCatching { of(id) }.getOrNull()
