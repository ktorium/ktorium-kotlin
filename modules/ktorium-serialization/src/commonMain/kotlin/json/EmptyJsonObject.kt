@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.serialization.json

import kotlinx.serialization.json.JsonObject
import org.ktorium.kotlin.ExperimentalKtoriumApi

private val emptyJsonObject = JsonObject(emptyMap())

/**
 * Returns an empty [JsonObject].
 */
@ExperimentalKtoriumApi
public fun emptyJsonObject(): JsonObject = emptyJsonObject
