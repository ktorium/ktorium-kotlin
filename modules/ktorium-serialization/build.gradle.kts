@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.config.ApiVersion
import org.jetbrains.kotlin.config.LanguageVersion
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import build.gradle.dsl.withCompilerArguments
import build.gradle.plugin.implementation

plugins {
    id(kotlinCatalog.plugins.multiplatform.get().pluginId)
    alias(libraryCatalog.plugins.kotlin.dokka)
    alias(libraryCatalog.plugins.kotlinx.kover)
    id("build-project-default")
    id("build-project-publication")
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
        moduleName = "ktorium-kotlin-serialization"

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
                api(project.dependencies.platform(libraryCatalog.build.kotlinx.serialization.bom))
                api(libraryCatalog.build.kotlinx.serialization.core)

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
