@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

gradle.beforeSettings {
    rootProject.name = "build-logic"
}

include("build-root")
include("build-wrapper-upgrade")
include("build-publication")