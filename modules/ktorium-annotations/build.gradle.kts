@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.ktorium.kotlin.gradle.dsl.withCompilerArguments

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")

    id("build-plugin")
    id("publication-plugin")
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
        moduleName = "ktorium-kotlin-annotations"

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
        }

        val commonTest by getting {
            kotlin {
                srcDirs("src/commonTest/kotlinX")
            }
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    withSourcesJar()
}
