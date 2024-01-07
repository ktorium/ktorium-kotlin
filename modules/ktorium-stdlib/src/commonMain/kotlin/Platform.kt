package org.ktorium.kotlin.stdlib

public interface Platform {
    public val name: String
}

public expect fun getPlatform(): Platform
