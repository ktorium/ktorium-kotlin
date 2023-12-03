import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.ktorium.kotlin.gradle.KotlinCompilerArgumentsBuilder

plugins {
    kotlin("multiplatform")
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
                apiVersion = "1.6"
                languageVersion = "1.9"
                progressiveMode = true

                optIn("kotlin.contracts.ExperimentalContracts")
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
    }

    tasks {
        project.tasks.withType<KotlinCompile> {
            compilerOptions {
                withCompilerArguments {
                    requiresOptIn()
                }
            }
        }
    }
}

internal fun KotlinJvmCompilerOptions.withCompilerArguments(configure: KotlinCompilerArgumentsBuilder.() -> Unit) {
    val arguments = KotlinCompilerArgumentsBuilder().apply(configure).build()

    freeCompilerArgs.addAll(arguments)
}
