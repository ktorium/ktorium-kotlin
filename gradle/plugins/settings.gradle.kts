@file:Suppress("UnstableApiUsage")

import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode = FAIL_ON_PROJECT_REPOS

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

gradle.beforeSettings {
    rootProject.name = "plugins"
}

include("build-plugin")
include("publication-plugin")
include("version-plugin")
include("wrapper-plugin")
