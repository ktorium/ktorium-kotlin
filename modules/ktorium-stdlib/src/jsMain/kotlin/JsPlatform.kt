package org.ktorium.kotlin.stdlib

public class JsPlatform : Platform {
    override val name: String = "JS"
}

public actual fun getPlatform(): Platform = JsPlatform()
