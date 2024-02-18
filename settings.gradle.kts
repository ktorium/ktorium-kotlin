@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("multiplatform") version "1.9.20" apply false
    kotlin("plugin.serialization") version "1.9.20" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.4" apply false
    id("org.jetbrains.dokka") version "1.9.10" apply false
    id("org.jetbrains.kotlinx.kover") version "0.7.5" apply false
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.13.2" apply false
}

gradle.beforeSettings {
    rootProject.name = "ktorium-kotlin"
}

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

fun includeBuild(directory: String, name: String) {
    require(name.isNotBlank())

    val buildModuleDirectory: File = rootDir.resolve("$directory/$name")

    includeBuild(buildModuleDirectory)
}
