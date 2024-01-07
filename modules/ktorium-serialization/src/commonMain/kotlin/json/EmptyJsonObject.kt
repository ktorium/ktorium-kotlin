@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.serialization.json

import kotlinx.serialization.json.JsonObject

private val emptyJsonObject = JsonObject(emptyMap())

/**
 * Returns an empty [JsonObject].
 */
public fun emptyJsonObject(): JsonObject = emptyJsonObject
