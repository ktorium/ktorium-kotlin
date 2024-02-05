@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.plugins.build

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import javax.inject.Inject

public abstract class BuildProperties @Inject constructor(
    private val providers: ProviderFactory,
) {
    public val mainJvmVersion: Provider<JvmTarget> = property("javaToolchain.mainJvmCompiler", JvmTarget::fromTarget)

    public val kotlinApiVersion: Provider<KotlinVersion> = property("kotlinApiVersion", KotlinVersion::fromVersion)

    public val kotlinLanguageVersion: Provider<KotlinVersion> = property("kotlinLanguageVersion", KotlinVersion::fromVersion)

    private fun <T : Any> property(name: String, convert: (String) -> T) =
        providers.gradleProperty("org.ktorium.kotlin.$name").map(convert)

    public companion object {
        public const val EXTENSION_NAME: String = "ktoriumBuild"
    }
}
