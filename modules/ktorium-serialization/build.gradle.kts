@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerToolOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(buildCatalog.plugins.org.jetbrains.kotlin.multiplatform)
    alias(buildCatalog.plugins.org.jetbrains.kotlin.plugin.serialization)
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
                api(project.dependencies.platform(applicationCatalog.kotlinx.serialization.bom))
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
