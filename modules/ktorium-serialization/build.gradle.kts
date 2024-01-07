import org.ktorium.kotlin.gradle.dsl.withCompilerArguments
import org.ktorium.kotlin.gradle.plugin.api
import org.ktorium.kotlin.gradle.plugin.implementation

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

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

    jvm {
        compilations.all {
            compilerOptions.configure {
                withCompilerArguments {
                    requiresOptIn()
                    requiresJsr305()
                }
            }
        }
    }

    withSourcesJar()
}
