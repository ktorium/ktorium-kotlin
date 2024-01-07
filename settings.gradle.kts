@file:Suppress("UnstableApiUsage")

pluginManagement {
    plugins {
        kotlin("multiplatform") version "1.9.22" apply false
        kotlin("plugin.serialization") version "1.9.22" apply false

        id("io.gitlab.arturbosch.detekt") version "1.23.4" apply false
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.PREFER_PROJECT

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
