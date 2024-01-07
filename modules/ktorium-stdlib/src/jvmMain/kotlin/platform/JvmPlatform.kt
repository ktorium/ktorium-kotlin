@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.stdlib.platform

public class JvmPlatform : Platform {
    override val name: String = "JVM"
}

public actual fun getPlatform(): Platform = JvmPlatform()
