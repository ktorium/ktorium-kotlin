package org.ktorium.kotlin.gradle.catalogs.build

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

public class BuildCatalogPlugin : Plugin<Settings> {
    public override fun apply(settings: Settings): Unit = with(settings) {
        dependencyResolutionManagement {
            versionCatalogs {
                create("buildCatalog") {
                    version("gradle", "8.6")
                    version("jvm", "17")

                    val kotlinVersion = version("kotlin", "1.9.22")
                    version("kotlin-jvm-target", "17")
                    version("kotlin-dsl-jvm-target", "17")
                    version("kotlin-api-version", "1.6")
                    version("kotlin-language-version", "2.0")

                    val detektVersion = version("detekt", "1.23.4")

                    val dokka = version("dokka", "1.9.10")

                    library("jetBrains-dokka-base", "org.jetbrains.dokka", "dokka-base").versionRef(dokka)
                    library("jetBrains-dokka-gradle", "org.jetbrains.dokka", "dokka-gradle-plugin").versionRef(dokka)

                    plugin("kotlin-multiplatform", "org.jetbrains.kotlin.multiplatform").versionRef(kotlinVersion)
                    plugin("detekt", "io.gitlab.arturbosch.detekt").versionRef(detektVersion)
                }
            }
        }
    }
}
