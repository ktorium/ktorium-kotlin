package org.ktorium.kotlin.stdlib

public class JvmPlatform : Platform {
    override val name: String = "JVM"
}

public actual fun getPlatform(): Platform = JvmPlatform()
