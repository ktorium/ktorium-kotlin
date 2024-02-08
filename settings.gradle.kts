@file:Suppress("UnstableApiUsage")

pluginManagement {
    plugins {
        kotlin("multiplatform") apply false
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

    includeBuild("gradle/catalogs/application-catalog")
    includeBuild("gradle/catalogs/build-catalog")
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.PREFER_PROJECT

    repositories {
        mavenCentral()
    }
}

plugins {
    id("build-catalog")
    id("application-catalog")
}

rootProject.name = "ktorium-kotlin"

includeBuild("gradle/plugins", "build-plugin")
includeBuild("gradle/plugins", "wrapper-plugin")
includeBuild("gradle/plugins", "version-plugin")
includeBuild("gradle/plugins", "publication-plugin")

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
