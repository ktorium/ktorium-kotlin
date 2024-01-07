@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.stdlib.platform

public class WasmJsPlatform : Platform {
    override val name: String = "WasmJs"
}

public actual fun getPlatform(): Platform = WasmJsPlatform()
