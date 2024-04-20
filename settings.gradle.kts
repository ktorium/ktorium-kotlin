import build.gradle.api.includeModule

pluginManagement {
    includeBuild("build-settings-logic")
    includeBuild("build-logic")
}

plugins {
    id("build-settings-default")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("buildCatalog") {
            from(files("./gradle/catalogs/buildCatalog.versions.toml"))
        }
    }
}

rootProject.name = "ktorium-kotlin"

includeModule("ktorium-stdlib")
includeModule("ktorium-coroutines")
includeModule("ktorium-serialization")
includeModule("ktorium-datetime")
includeModule("ktorium-io")
includeModule("ktorium-annotations")
