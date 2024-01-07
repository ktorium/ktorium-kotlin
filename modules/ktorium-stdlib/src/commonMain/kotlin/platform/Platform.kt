@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.stdlib.platform

public interface Platform {
    public val name: String
}

public expect fun getPlatform(): Platform
