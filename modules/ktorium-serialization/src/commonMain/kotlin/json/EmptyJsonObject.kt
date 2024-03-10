@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.serialization.json

import kotlinx.serialization.json.JsonObject
import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

private val emptyJsonObject = JsonObject(emptyMap())

/**
 * Returns an empty [JsonObject].
 */
@ExperimentalSince(Unreleased)
public fun emptyJsonObject(): JsonObject = emptyJsonObject
