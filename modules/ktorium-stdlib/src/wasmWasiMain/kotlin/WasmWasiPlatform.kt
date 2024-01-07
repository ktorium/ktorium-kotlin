package org.ktorium.kotlin.stdlib

public class WasmWasiPlatform : Platform {
    override val name: String = "WasmWasi"
}

public actual fun getPlatform(): Platform = WasmWasiPlatform()
