@file:Suppress("UnstableApiUsage")

pluginManagement {
    plugins {
        kotlin("multiplatform") version "1.9.22" apply false
        kotlin("plugin.serialization") version "1.9.22" apply false
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        mavenCentral()
    }
}

rootProject.name = "ktorium-kotlin"

includeModule("ktorium-stdlib")
includeModule("ktorium-coroutines")
includeModule("ktorium-serialization")
includeModule("ktorium-platform")
includeModule("ktorium-datetime")

fun includeModule(name: String) {
    include(name)
    project(":${name}").projectDir = File("modules/$name")
}
