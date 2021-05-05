import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("multiplatform") version "1.5.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.17.0-RC2" apply false
}

allprojects {
    group = "com.ktorium.kotlin"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }

    configurations.all {
        resolutionStrategy {
            failOnNonReproducibleResolution()
        }
    }
}

tasks {
    named<Wrapper>("wrapper") {
        gradleVersion = rootProject.property("gradle-wrapper.version") as String
        distributionType = ALL
    }
}
