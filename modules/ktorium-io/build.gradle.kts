@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerToolOptions
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(buildCatalog.plugins.org.jetbrains.kotlin.multiplatform)
    alias(buildCatalog.plugins.org.jetbrains.dokka)
    alias(buildCatalog.plugins.org.jetbrains.kotlinx.kover)

    id("build-plugin")
    id("publication-plugin")
}

configurations.all {
    resolutionStrategy {
        failOnNonReproducibleResolution()
    }
}

public class KotlinCompilerArgumentsBuilder {
    private val arguments: MutableList<String> = mutableListOf()

    public fun add(arg: String): Boolean = arguments.add(arg)
    public fun requiresOptIn(): Boolean = arguments.add("-opt-in=kotlin.RequiresOptIn")
    public fun requiresJsr305(value: String = "strict"): Boolean = arguments.add("-Xjsr305=$value")
    public fun suppressExpectActualClasses(): Boolean = arguments.add("-Xexpect-actual-classes")
    public fun suppressVersionWarnings(): Boolean = arguments.add("-Xsuppress-version-warnings")

    public fun build(): List<String> = arguments
}

public fun KotlinCommonCompilerToolOptions.withCompilerArguments(configure: KotlinCompilerArgumentsBuilder.() -> Unit) {
    val arguments = KotlinCompilerArgumentsBuilder().apply(configure).build()

    freeCompilerArgs.addAll(arguments)
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
        moduleName = "ktorium-kotlin-io"

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
                apiVersion = buildCatalog.versions.kotlin.api.version.get()
                languageVersion = buildCatalog.versions.kotlin.language.version.get()
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
                api(applicationCatalog.kotlinx.io)

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
