package org.ktorium.kotlin.gradle.catalogs.application

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

public class ApplicationCatalogPlugin : Plugin<Settings> {
    public override fun apply(settings: Settings): Unit = with(settings) {
        dependencyResolutionManagement {
            versionCatalogs {
                create("applicationCatalog") {
                    version("kotlinx-serialization-version", "1.6.2")
                    version("kotlinx-io-version", "0.3.0")
                    version("kotlinx-datetime-version", "0.5.0")
                    version("kotlinx-coroutines-bom", "1.7.3")

                    library("kotlinx-serialization-bom", "org.jetbrains.kotlinx", "kotlinx-serialization-bom").versionRef("kotlinx-serialization-version")
                    library("kotlinx-coroutines-bom", "org.jetbrains.kotlinx", "kotlinx-coroutines-bom").versionRef("kotlinx-coroutines-bom")
                    library("kotlinx-io", "org.jetbrains.kotlinx", "kotlinx-io-core").versionRef("kotlinx-io-version")
                    library("kotlinx-datetime", "org.jetbrains.kotlinx", "kotlinx-datetime").versionRef("kotlinx-datetime-version")
                }
                create("testCatalog")
            }
        }
    }
}
