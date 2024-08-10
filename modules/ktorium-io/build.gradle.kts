@file:OptIn(ExperimentalWasmDsl::class)

import build.gradle.dsl.withCompilerArguments
import org.jetbrains.kotlin.config.ApiVersion
import org.jetbrains.kotlin.config.LanguageVersion
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    id(libraries.plugins.kotlin.multiplatform.get().pluginId)
    alias(libraries.plugins.kotlin.dokka)
    alias(libraries.plugins.kotlinx.kover)
    id("build-project-default")
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
            }
        }

        jvmToolchain {
            val mainJvmCompiler = providers.gradleProperty("kotlin.javaToolchain.mainJvmCompiler").map(JavaLanguageVersion::of)

            languageVersion = mainJvmCompiler
        }
    }

    wasmJs {
        moduleName = "ktorium-kotlin-io"

        browser {
            testTask {
                useKarma {
                    useConfigDirectory(rootDir.resolve("gradle/js/karma"))
                    useChromeHeadless()
                }
            }
        }

        binaries.library()
    }

    wasmWasi {
        nodejs()
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
                    useConfigDirectory(rootDir.resolve("gradle/js/karma"))
                    useChromeHeadless()
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
                api(libraries.build.kotlinx.io.core)

                api(project(":ktorium-annotations"))
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
