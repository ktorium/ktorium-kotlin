@file:Suppress("PackageDirectoryMismatch", "UnstableApiUsage")

package build.gradle.plugins.settings

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.kotlin.dsl.maven

private val Settings.log: Logger by lazy { Logging.getLogger(Settings::class.java) }

public class SettingsKotlinVersionCatalogPlugin : Plugin<Settings> {
    override fun apply(settings: Settings): Unit = settings.run {
        val kotlinVersionOverride = providers.gradleProperty("kotlinVersionOverride").orNull?.takeIf(String::isNotBlank)

        if (kotlinVersionOverride != null) {
            log.lifecycle("Kotlin version override: $kotlinVersionOverride")
        }

        pluginManagement {
            if (kotlinVersionOverride != null) {
                repositories {
                    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev") {
                        content {
                            includeGroupAndSubgroups("org.jetbrains.kotlin")
                        }
                    }
                }
            }
        }

        dependencyResolutionManagement {
            if (kotlinVersionOverride != null) {
                repositories {
                    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev") {
                        content {
                            includeGroupAndSubgroups("org.jetbrains.kotlin")
                        }
                    }
                }
            }

            versionCatalogs {
                create("kotlinCatalog") {
                    val kotlin = version("kotlin", kotlinVersionOverride ?: "1.9.22")  // TODO

                    library("gradle-plugin", "org.jetbrains.kotlin", "kotlin-gradle-plugin").versionRef(kotlin)

                    plugin("multiplatform", "org.jetbrains.kotlin.multiplatform").versionRef(kotlin)
                    plugin("jvm", "org.jetbrains.kotlin.jvm").versionRef(kotlin)
                    plugin("serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef(kotlin)
                }
            }
        }
    }
}
