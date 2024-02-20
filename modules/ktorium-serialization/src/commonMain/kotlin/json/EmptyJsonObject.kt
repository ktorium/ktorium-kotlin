@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.serialization.json

import kotlinx.serialization.json.JsonObject
import org.ktorium.kotlin.ExperimentalKtoriumAPI

private val emptyJsonObject = JsonObject(emptyMap())

/**
 * Returns an empty [JsonObject].
 */
@ExperimentalKtoriumAPI
public fun emptyJsonObject(): JsonObject = emptyJsonObject
