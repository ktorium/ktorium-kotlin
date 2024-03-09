@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("../build-logic-settings")
}

plugins {
    id("build-settings-default")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libraryCatalog") {
            from(files("../gradle/catalogs/library.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"

include("build-project-plugin")
