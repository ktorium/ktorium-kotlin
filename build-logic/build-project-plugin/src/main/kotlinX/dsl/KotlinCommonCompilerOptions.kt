@file:Suppress("PackageDirectoryMismatch")

package build.gradle.dsl

import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerOptions

public fun KotlinCommonCompilerOptions.withCompilerArguments(configure: KotlinCompilerArgumentsBuilder.() -> Unit) {
    val arguments = KotlinCompilerArgumentsBuilder().apply(configure).build()

    freeCompilerArgs.addAll(arguments)
}
