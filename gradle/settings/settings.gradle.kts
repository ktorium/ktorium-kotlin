@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.PREFER_PROJECT

    repositories {
        mavenCentral()
    }
}

gradle.beforeSettings {
    rootProject.name = "settings"
}

include("root-settings-plugins")
