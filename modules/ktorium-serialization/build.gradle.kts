@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.ktorium.kotlin.gradle.dsl.withCompilerArguments
import org.ktorium.kotlin.gradle.plugin.api
import org.ktorium.kotlin.gradle.plugin.implementation

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")

    id("build.base")
    id("org.ktorium.kotlin.gradle.plugins.publication")
}

configurations.all {
    resolutionStrategy {
        failOnNonReproducibleResolution()
    }
}

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
                apiVersion = ktoriumBuild.kotlinApiVersion.get().version
                languageVersion = ktoriumBuild.kotlinLanguageVersion.get().version
                progressiveMode = true

                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.RequiresOptIn")
            }
        }

        val commonMain by getting {
            kotlin {
                srcDirs( "src/commonMain/kotlinX")
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
                srcDirs( "src/commonTest/kotlinX")
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
