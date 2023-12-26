package org.ktorium.kotlin.gradle.builder

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import javax.inject.Inject


abstract class BuildProperties @Inject constructor(
    private val providers: ProviderFactory,
) {
    val mainJavaVersion: Provider<JavaLanguageVersion> = property("javaToolchain.mainCompiler", JavaLanguageVersion::of)

    val kotlinApiVersion: Provider<KotlinVersion> = property("kotlinApiVersion", KotlinVersion::fromVersion)

    val kotlinLanguageVersion: Provider<KotlinVersion> = property("kotlinLanguageVersion", KotlinVersion::fromVersion)

    private fun <T : Any> property(name: String, convert: (String) -> T) = providers.gradleProperty("org.ktorium.kotlin.$name").map(convert)

    companion object {
        const val EXTENSION_NAME = "ktoriumBuild"
    }
}