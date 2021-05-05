package org.gradle.api.artifacts.dsl

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency

/**
 * Builds the dependency notation for the named KotlinX [module] at the given [version].
 *
 * @param module simple name of the KotlinX module, for example "coroutines-core".
 * @param version optional desired version, unspecified if null.
 */
@Suppress("detekt.UnusedPrivateMember")
fun DependencyHandler.kotlinX(module: String, version: String? = null): String =
        "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" } ?: ""}"

@Suppress("detekt.UnusedPrivateMember")
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }

    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T
