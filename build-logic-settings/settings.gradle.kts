@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    versionCatalogs {
        create("libraryCatalog") {
            from(files("../gradle/catalogs/library.versions.toml"))
        }
    }
}

rootProject.name = "build-settings-logic"

include("build-settings-plugin")
