@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.stdlib.platform

public actual object RuntimePlatform {
    public actual fun currentPlatform(): Platform = Platform.JS
}
