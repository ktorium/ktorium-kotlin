package org.ktorium.kotlin.stdlib

public class WasmJsPlatform : Platform {
    override val name: String = "WasmJs"
}

public actual fun getPlatform(): Platform = WasmJsPlatform()
