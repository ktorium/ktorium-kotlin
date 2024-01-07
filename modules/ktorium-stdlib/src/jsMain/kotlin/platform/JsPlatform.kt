@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.stdlib.platform

public class JsPlatform : Platform {
    override val name: String = "JS"
}

public actual fun getPlatform(): Platform = JsPlatform()
