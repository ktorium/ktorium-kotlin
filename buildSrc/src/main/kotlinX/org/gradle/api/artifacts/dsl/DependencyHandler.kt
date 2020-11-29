package org.gradle.api.artifacts.dsl

/**
 * Builds the dependency notation for the named KotlinX [module] at the given [version].
 *
 * @param module simple name of the KotlinX module, for example "coroutines-core".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.kotlinX(module: String, version: String? = null): String =
        "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" } ?: ""}"
