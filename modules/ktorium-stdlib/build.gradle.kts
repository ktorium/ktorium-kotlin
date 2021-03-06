import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    repositories {
        mavenCentral()
    }
}


plugins {
    kotlin("multiplatform")
    id("io.gitlab.arturbosch.detekt")
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
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs = listOf(
                        "-Xjsr305=strict"
                )
            }
        }

        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }

    js {
        compilations.all {
            kotlinOptions {
                moduleKind = "umd"
                sourceMap = true
                metaInfo = true
            }
        }

        browser {
            testTask {
                enabled = false
                useKarma {
                    useChromeHeadless()
                }
            }
        }

        binaries.executable()
    }

    sourceSets {
        all {
            languageSettings.apply {
                languageVersion = "1.5"
                apiVersion = "1.5"
                progressiveMode = true

                val isTest = name.endsWith("Test")
                if(isTest) {
                    useExperimentalAnnotation("kotlin.contracts.ExperimentalContracts")
                }
            }
        }

        val commonMain by getting {
            kotlin.srcDirs("src/commonMain/kotlinX")
        }

        val commonTest by getting {
            kotlin.srcDirs("src/commonTest/kotlinX")

            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlinX")
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        val jsMain by getting {
            kotlin.srcDirs("src/jsMain/kotlinX")
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

tasks {
    val detekt by getting(Detekt::class) {
        config.setFrom(files("${rootProject.rootDir}/config/detekt/detekt.yml"))
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        buildUponDefaultConfig = false
        parallel = true
        jvmTarget = "11"
        reports {
            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
        }
    }
}
