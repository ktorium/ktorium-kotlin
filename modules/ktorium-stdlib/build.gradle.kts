import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.ktorium.kotlin.gradle.dsl.withCompilerArguments

plugins {
    kotlin("multiplatform")

    id("build.base")
    id("build.publication")
}

configurations.all {
    resolutionStrategy {
        failOnNonReproducibleResolution()
    }
}

kotlin {
    explicitApi()

    jvm {
        compilations.all {
            compilerOptions.configure {
                withCompilerArguments {
                    requiresOptIn()
                    requiresJsr305()
                }
                jvmTarget.set(ktoriumBuild.mainJvmVersion)
            }
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmWasi {
        nodejs()

        binaries.library()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        nodejs()

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
                useMocha {
                    timeout = "10s"
                }
            }
        }
        nodejs {
            testTask {
                useMocha {
                    timeout = "10s"
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
                srcDirs("src/commonMain/kotlinX")
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

        val wasmJsMain by getting
        val wasmWasiMain by getting
    }

    withSourcesJar()
}
