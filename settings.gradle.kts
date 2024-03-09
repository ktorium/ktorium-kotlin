@file:Suppress("UnstableApiUsage")

import build.gradle.api.includeModule

pluginManagement {
    includeBuild("build-logic-settings")
    includeBuild("build-logic")
}

plugins {
    id("build-settings-default")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libraryCatalog") {
            from(files("./gradle/catalogs/library.versions.toml"))
        }
    }
}

rootProject.name = "ktorium-kotlin"

includeModule("ktorium-stdlib")
includeModule("ktorium-coroutines")
includeModule("ktorium-serialization")
includeModule("ktorium-platform")
includeModule("ktorium-datetime")
includeModule("ktorium-io")
includeModule("ktorium-annotations")
includeModule("ktorium-version-catalog")
