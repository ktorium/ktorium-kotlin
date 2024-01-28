@file:Suppress("UnstableApiUsage")

pluginManagement {
    plugins {
        kotlin("multiplatform") version "1.9.22" apply false
        kotlin("plugin.serialization") version "1.9.22" apply false
        id("io.gitlab.arturbosch.detekt") version "1.23.4" apply false
        id("org.jetbrains.dokka") version "1.9.10" apply false
        id("org.jetbrains.kotlinx.kover") version "0.7.5" apply false
        id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.13.2" apply false
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

includePlugin("wrapper")
includePlugin("version")
includePlugin("publication")

includeModule("ktorium-stdlib")
includeModule("ktorium-coroutines")
includeModule("ktorium-serialization")
includeModule("ktorium-platform")
includeModule("ktorium-datetime")
includeModule("ktorium-io")
includeModule("ktorium-annotations")

fun includeModule(name: String) {
    require(name.isNotBlank())

    include(name)
    project(":${name}").projectDir = rootDir.resolve("modules/$name")
}

fun includePlugin(name: String) {
    require(name.isNotBlank())

    val gradlePluginDirectory: File = rootDir.resolve("gradle/plugins/$name")

    includeBuild(gradlePluginDirectory)
}
