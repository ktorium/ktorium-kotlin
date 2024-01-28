@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.serialization.json

import kotlinx.serialization.json.JsonObject
import org.ktorium.kotlin.ExperimentalKtorium

private val emptyJsonObject = JsonObject(emptyMap())

/**
 * Returns an empty [JsonObject].
 */
@ExperimentalKtorium
public fun emptyJsonObject(): JsonObject = emptyJsonObject
