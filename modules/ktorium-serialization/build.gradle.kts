@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.config.ApiVersion
import org.jetbrains.kotlin.config.LanguageVersion
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.ktorium.kotlin.gradle.dsl.withCompilerArguments

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")
    id("build-plugin")
    id("publication-plugin")
}

configurations.all {
    resolutionStrategy {
        failOnNonReproducibleResolution()
    }
}

private fun notation(group: String, name: String, version: String? = null) =
    "$group:$name${version?.let { ":$version" } ?: ""}"

public fun KotlinDependencyHandler.api(group: String, name: String, version: String? = null): Dependency? =
    api(notation(group, name, version))

public fun KotlinDependencyHandler.compileOnly(group: String, name: String, version: String? = null): Dependency? =
    compileOnly(notation(group, name, version))

public fun KotlinDependencyHandler.implementation(group: String, name: String, version: String? = null): Dependency? =
    implementation(notation(group, name, version))

public fun KotlinDependencyHandler.runtimeOnly(group: String, name: String, version: String? = null): Dependency? =
    runtimeOnly(notation(group, name, version))


kotlin {
    explicitApi()

    targets.all {
        compilations.all {
            compilerOptions.configure {
                withCompilerArguments {
                    requiresOptIn()
                    suppressExpectActualClasses()
                    suppressVersionWarnings()
                }
            }
        }
    }

    jvm {
        compilations.all {
            compilerOptions.configure {
                withCompilerArguments {
                    requiresJsr305()
                }
                jvmTarget.set(ktoriumBuild.mainJvmVersion)
            }
        }
    }

    wasmJs {
        moduleName = "ktorium-kotlin-serialization"

        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useConfigDirectory(project.projectDir.resolve("karma.config.d").resolve("wasm"))
                }
            }
        }

        binaries.library()
    }

    js {
        compilations.all {
            kotlinOptions {
                sourceMap = true
                moduleKind = "umd"
                metaInfo = true
            }
        }

        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useConfigDirectory(project.projectDir.resolve("karma.config.d").resolve("js"))
                }
            }
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                apiVersion = ApiVersion.KOTLIN_1_6.toString()
                languageVersion = LanguageVersion.KOTLIN_2_0.toString()
                progressiveMode = true

                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.RequiresOptIn")
            }
        }

        val commonMain by getting {
            kotlin {
                srcDirs("src/commonMain/kotlinX")
            }
            dependencies {
                api(project.dependencies.platform("org.jetbrains.kotlinx:kotlinx-serialization-bom:1.6.2"))
                api("org.jetbrains.kotlinx", "kotlinx-serialization-core")
                api("org.jetbrains.kotlinx", "kotlinx-serialization-json")

                api(project(":ktorium-annotations"))
            }
        }

        val commonTest by getting {
            kotlin {
                srcDirs("src/commonTest/kotlinX")
            }
            dependencies {
                implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json")
                implementation("org.jetbrains.kotlinx", "kotlinx-serialization-protobuf")
                implementation(kotlin("test"))
            }
        }
    }

    withSourcesJar()
}
