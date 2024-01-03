import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.ktorium.kotlin.gradle.KotlinCompilerArgumentsBuilder
import org.ktorium.kotlin.gradle.plugin.api

plugins {
    kotlin("multiplatform")

    id("build.base")
}

configurations.all {
    resolutionStrategy {
        failOnNonReproducibleResolution()
    }
}

kotlin {
    explicitApi()

    jvm()

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
            dependencies {
                api(project.dependencies.platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.7.3"))
                api("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
            }
        }

        val commonTest by getting {
            kotlin {
                srcDirs("src/commonTest/kotlinX")
            }
            dependencies {
                api("org.jetbrains.kotlinx", "kotlinx-coroutines-test")
                implementation(kotlin("test"))
            }
        }
    }

    tasks {
        project.tasks.withType<KotlinCompile> {
            compilerOptions {
                withCompilerArguments {
                    requiresOptIn()
                    requiresJsr305()
                }
            }
        }
    }
}

internal fun KotlinJvmCompilerOptions.withCompilerArguments(configure: KotlinCompilerArgumentsBuilder.() -> Unit) {
    val arguments = KotlinCompilerArgumentsBuilder().apply(configure).build()

    freeCompilerArgs.addAll(arguments)
}
