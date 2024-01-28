@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.stdlib.platform

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * List of supported Kotlin runtime platforms.
 */
public enum class Platform {
    JVM,
    JS,
    WASM_JS,
    WASM_WASI,
}

@ExperimentalKtorium
public expect object RuntimePlatform {
    /**
     * Get the current Kotlin runtime platform.
     */
    public fun currentPlatform(): Platform
}
