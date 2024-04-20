@file:OptIn(ExperimentalWasmDsl::class)

import build.gradle.dsl.withCompilerArguments
import org.jetbrains.kotlin.config.ApiVersion
import org.jetbrains.kotlin.config.LanguageVersion
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    id(buildCatalog.plugins.kotlin.multiplatform.get().pluginId)
    alias(buildCatalog.plugins.kotlin.dokka)
    alias(buildCatalog.plugins.kotlinx.kover)
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
        moduleName = "ktorium-kotlin-stdlib"

        browser {
            testTask {
                useKarma {
                    useConfigDirectory(rootDir.resolve("gradle/js/karma"))
                    useChromeHeadless()
                }
            }
        }
        nodejs()

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

        binaries.library()
    }

    sourceSets {
        applyDefaultHierarchyTemplate()

        all {
            languageSettings.apply {
                apiVersion = ApiVersion.KOTLIN_1_6.toString()
                languageVersion = LanguageVersion.KOTLIN_2_0.toString()
                progressiveMode = true

                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.RequiresOptIn")
                optIn("org.ktorium.kotlin.ExperimentalKtoriumAPI")
                optIn("org.ktorium.kotlin.InternalKtoriumAPI")
            }
        }

        val commonMain by getting {
            kotlin {
                srcDirs("src/commonMain/kotlinX")
            }
            dependencies {
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

        val wasmCommonMain by creating {
            dependsOn(commonMain)
        }

        val wasmJsMain by getting {
            dependsOn(wasmCommonMain)
        }

        val wasmWasiMain by getting {
            dependsOn(wasmCommonMain)
        }
    }

    withSourcesJar()
}
